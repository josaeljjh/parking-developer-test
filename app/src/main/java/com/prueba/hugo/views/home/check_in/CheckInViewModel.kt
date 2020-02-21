package com.prueba.hugo.views.home.check_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.prueba.hugo.App
import com.prueba.hugo.R
import com.prueba.hugo.db.DataCar
import com.prueba.hugo.interfaces.InterfaceGlobal
import com.prueba.hugo.repository.DataRepository
import com.prueba.hugo.views.home.HomeActivity

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

class CheckInViewModel(private val repository: DataRepository): ViewModel() {

    var imageRes = R.drawable.cars_header_green
    private var main: InterfaceGlobal.mainGlobal = HomeActivity()
    var textNPlaca = MutableLiveData<String>()
    val editText = Observer<String>{}

    init {
        textNPlaca.postValue("")
        textNPlaca.observeForever(editText)
    }
    override fun onCleared() {
        textNPlaca.removeObserver(editText)
    }

    fun validEntry(){
        if(textNPlaca.value!!.isNotEmpty()){
            registerEntry()
        }else{
            //Toast.makeText(App.context,App.context.getString(R.string.error_plate), Toast.LENGTH_SHORT).show()
            main.onSnackNavigationBar(App.context.getString(R.string.error_plate))
        }
    }

    fun registerEntry(){
        val data =   repository.getDataId(textNPlaca.value!!)
        if(data.isNotEmpty()){
            val time = data[0]?.timeStart?: 0
            if(time <= 0){
                val dataCar = DataCar()
                dataCar.id = data[0]!!.id
                dataCar.user =  data[0]!!.user
                dataCar.timeStart = System.currentTimeMillis()
                repository.createData(dataCar)
                main.onSnackNavigationBar(App.context.getString(R.string.text_ingreso))
                textNPlaca.postValue("")
            }else{
                main.onSnackNavigationBar(App.context.getString(R.string.marcar_salida))
            }
        }else{
            main.onSnackNavigationBar(App.context.getString(R.string.no_existe))
        }
    }

}