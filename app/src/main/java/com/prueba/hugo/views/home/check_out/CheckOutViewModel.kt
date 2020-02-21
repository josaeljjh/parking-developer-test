package com.prueba.hugo.views.home.check_out

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.prueba.hugo.App
import com.prueba.hugo.R
import com.prueba.hugo.db.DataCar
import com.prueba.hugo.interfaces.InterfaceGlobal
import com.prueba.hugo.repository.DataRepository
import com.prueba.hugo.utils.Cons
import com.prueba.hugo.utils.SingleLiveEvent
import com.prueba.hugo.utils.extensions.formatMin
import com.prueba.hugo.views.home.HomeActivity
import io.realm.RealmResults
import kotlin.math.abs
import kotlin.math.floor

/**
 * Created by Josael Hernandez on 2020-02-21.
 * Contact : josaeljjh@gmail.com
 */

class CheckOutViewModel(private val repository: DataRepository): ViewModel() {

    var imageRes = R.drawable.cars_header_yellow
    var imageExit = R.drawable.car_exit
    private var main: InterfaceGlobal.mainGlobal = HomeActivity()
    var nPlacaExit = MutableLiveData<String>()
    val editText = Observer<String>{}
    var progress = 100.0f
    val launchPay = SingleLiveEvent<String>()
    init {
        nPlacaExit.postValue("")
        nPlacaExit.observeForever(editText)
    }
    override fun onCleared() {
        nPlacaExit.removeObserver(editText)
    }

    fun validEntry(){
        if(nPlacaExit.value!!.isNotEmpty()){
            registerExit()
        }else{
            main.onSnackNavigationBar(App.context.getString(R.string.error_plate))
        }
    }

    fun registerExit() {
        val data = repository.getDataId(nPlacaExit.value!!)
        if (data.isNotEmpty()) {
            val time = data[0]?.timeStart?: 0
            if(time > 0) {
                val dataCar = DataCar()
                dataCar.id = data[0]!!.id
                dataCar.user = data[0]!!.user
                dataCar.timeStart = data[0]!!.timeStart
                dataCar.timeEnd = System.currentTimeMillis()
                repository.createData(dataCar)
                main.onSnackNavigationBar(App.context.getString(R.string.text_exit))
                checkTotal()
            }else{
                main.onSnackNavigationBar(App.context.getString(R.string.salida))
            }
        } else {
            main.onSnackNavigationBar(App.context.getString(R.string.no_existe))
        }
    }

    private fun checkTotal() {
        val data = repository.getDataId(nPlacaExit.value!!)
        if (data.isNotEmpty()) {
            val time = abs(data[0]!!.timeEnd!! - data[0]!!.timeStart!!)
            val minutes = floor((time.toDouble()/1000)/60).toInt()
            val timeTotal:Int = (data[0]!!.timeTotal!! + minutes)
            main.onSnackNavigationBar(formatMin(timeTotal))
            when (data[0]!!.user) {
                Cons.OFICIAL -> totalUpdate(data, timeTotal)
                Cons.RESIDENTE -> totalUpdate(data, timeTotal)
                Cons.NO_RESIDENTE -> {
                    totalUpdate(data, minutes)
                    launchPay.postValue(nPlacaExit.value!!)
                }
            }
        }
    }

    fun totalUpdate(data: RealmResults<DataCar>, total: Int) {
        if (data.isNotEmpty()) {
            val dataCar = DataCar()
            dataCar.id = data[0]!!.id
            dataCar.user = data[0]!!.user
            dataCar.timeStart = data[0]!!.timeStart
            dataCar.timeEnd = data[0]!!.timeEnd
            dataCar.timeTotal = total
            repository.createData(dataCar)
        }
    }
}