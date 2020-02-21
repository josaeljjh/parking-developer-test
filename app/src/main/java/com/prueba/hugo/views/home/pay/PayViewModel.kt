package com.prueba.hugo.views.home.pay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.prueba.hugo.App
import com.prueba.hugo.R
import com.prueba.hugo.db.DataCar
import com.prueba.hugo.interfaces.InterfaceGlobal
import com.prueba.hugo.repository.DataRepository
import com.prueba.hugo.utils.Cons
import com.prueba.hugo.utils.extensions.ConvertDollar
import com.prueba.hugo.utils.extensions.formatMin
import com.prueba.hugo.views.home.HomeActivity
import io.realm.RealmResults

/**
 * Created by Josael Hernandez on 2020-02-21.
 * Contact : josaeljjh@gmail.com
 */

class PayViewModel(private val repository: DataRepository) : ViewModel() {

    var imageRes = R.drawable.logo
    private var main: InterfaceGlobal.mainGlobal = HomeActivity()
    var nPlacaPay = MutableLiveData<String>()
    val editText = Observer<String> {}
    var time = MutableLiveData<String>()
    val editTime = Observer<String> {}
    var amount = MutableLiveData<String>()
    val editAmount = Observer<String> {}

    init {
        nPlacaPay.postValue("")
        nPlacaPay.observeForever(editText)
        time.postValue("00:00 min")
        time.observeForever(editTime)
        amount.postValue("$0.00")
        amount.observeForever(editAmount)
    }

    override fun onCleared() {
        nPlacaPay.removeObserver(editText)
        time.removeObserver(editTime)
        amount.removeObserver(editAmount)
    }

    fun validEntry() {
        if (nPlacaPay.value!!.isNotEmpty()) {
            calcularPago(nPlacaPay.value!!)
        } else {
            main.onSnackNavigationBar(App.context.getString(R.string.error_plate))
        }
    }

    fun calcularPago(placa: String) {
        nPlacaPay.postValue(placa)
        val data = repository.getDataId(placa)
        if (data.isNotEmpty()) {

            val salida = data[0]?.timeEnd?: 0
            if(salida > 0) {
                val timeTotal = data[0]!!.timeTotal!!
                when (data[0]!!.user) {
                    Cons.OFICIAL -> {
                        time.postValue(formatMin(timeTotal))
                        val monto = timeTotal * Cons.TIME_OFICIAL
                        amount.postValue(monto.ConvertDollar())
                    }
                    Cons.RESIDENTE -> {
                        time.postValue(formatMin(timeTotal))
                        val monto = timeTotal * Cons.TIME_RESIDENTE
                        amount.postValue(monto.ConvertDollar())
                    }
                    Cons.NO_RESIDENTE -> {
                        time.postValue(formatMin(timeTotal))
                        val monto = timeTotal * Cons.TIME_NO_RESIDENTE
                        amount.postValue(monto.ConvertDollar())
                    }
                }
            }else{
                main.onSnackNavigationBar(App.context.getString(R.string.pago_salida))
            }
        } else {
            main.onSnackNavigationBar(App.context.getString(R.string.no_existe))
        }
    }

    fun Pay() {
        if (!time.value!!.equals("00:00 min", true)) {
            val data = repository.getDataId(nPlacaPay.value!!)
            if (data.isNotEmpty()) {
                if (data[0]!!.user!!.equals(Cons.NO_RESIDENTE, true)) {
                    if (repository.deleteDataId(data[0]!!.id!!)) {
                        main.onSnackNavigationBar(App.context.getString(R.string.pago_exitoso))
                    } else {
                        main.onSnackNavigationBar(App.context.getString(R.string.pago_fallido))
                    }
                } else {
                    updateUser(data)
                    main.onSnackNavigationBar(App.context.getString(R.string.pago_exitoso))
                }
                clearPay()
            } else {
                main.onSnackNavigationBar(App.context.getString(R.string.no_existe))
            }
        } else {
            main.onSnackNavigationBar(App.context.getString(R.string.error_pay))
        }
    }

    private fun updateUser(
        data: RealmResults<DataCar>
    ) {
        val dataCar = DataCar()
        dataCar.id = data[0]!!.id
        dataCar.user = data[0]!!.user
        dataCar.timeStart = 0
        dataCar.timeEnd = 0
        dataCar.timeTotal = 0
        repository.createData(dataCar)
    }

    private fun clearPay(){
        nPlacaPay.postValue("")
        time.postValue("00:00 min")
        amount.postValue("$0.00")
    }
}