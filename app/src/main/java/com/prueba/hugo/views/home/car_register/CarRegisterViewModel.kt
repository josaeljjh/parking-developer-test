package com.prueba.hugo.views.home.car_register

import android.widget.RadioGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.prueba.hugo.App
import com.prueba.hugo.R
import com.prueba.hugo.db.DataCar
import com.prueba.hugo.interfaces.InterfaceGlobal
import com.prueba.hugo.repository.DataRepository
import com.prueba.hugo.utils.Cons.NO_RESIDENTE
import com.prueba.hugo.utils.Cons.OFICIAL
import com.prueba.hugo.utils.Cons.RESIDENTE
import com.prueba.hugo.utils.Cons.TIME_NO_RESIDENTE
import com.prueba.hugo.utils.Cons.TIME_OFICIAL
import com.prueba.hugo.utils.Cons.TIME_RESIDENTE
import com.prueba.hugo.utils.extensions.ConvertDollar
import com.prueba.hugo.views.home.HomeActivity


/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

class CarRegisterViewModel(private val repository: DataRepository) : ViewModel() {

    var imageRes = R.drawable.cars_header_red
    private var main: InterfaceGlobal.mainGlobal = HomeActivity()
    var nPlacaRegister = MutableLiveData<String>()
    var tiempo = MutableLiveData<String>()
    //var radio_checked = MutableLiveData<Boolean>()
    private var selector: Boolean = false
    private val editText = Observer<String> {}
    private val editTextTime = Observer<String> {}
    private var typeUser =  MutableLiveData<String>()

    init {
        nPlacaRegister.postValue("")
        nPlacaRegister.observeForever(editText)
        tiempo.postValue("$0.00/min.")
        typeUser.postValue("")
    }

    override fun onCleared() {
        nPlacaRegister.removeObserver(editText)
        tiempo.removeObserver(editTextTime)
    }

    fun validEntry() {
        if (nPlacaRegister.value!!.isNotEmpty()) {
            if(selector){
                //createRegister()
                createUser()
            }else{
                main.onSnackNavigationBar(App.context.getString(R.string.error_user))
            }
        } else {
            main.onSnackNavigationBar(App.context.getString(R.string.error_plate))
        }
    }

    fun onSplitTypeChanged(radioGroup: RadioGroup, checkedId: Int) {
        selector = when(checkedId){
            R.id.checkOfficial -> {
                tiempo.postValue("${TIME_OFICIAL.ConvertDollar()}/min.")
                typeUser.postValue(OFICIAL)
                true
            }
            R.id.checkResident -> {
                tiempo.postValue("${TIME_RESIDENTE.ConvertDollar()}/min.")
                typeUser.postValue(RESIDENTE)
                true
            }
            R.id.checkNoResident -> {
                tiempo.postValue("${TIME_NO_RESIDENTE.ConvertDollar()}/min.")
                typeUser.postValue(NO_RESIDENTE)
                true
            }
            else -> false
        }
    }

    private fun createUser(){
        val data =   repository.getDataId(nPlacaRegister.value!!)
        if(data.isEmpty()){
            val dataCar = DataCar()
            dataCar.id = nPlacaRegister.value
            dataCar.user = typeUser.value
            dataCar.timeStart = 0
            dataCar.timeEnd = 0
            //create data
            repository.createData(dataCar)
            verifyData()
        }else{
            main.onSnackNavigationBar(App.context.getString(R.string.ya_existe))
        }
    }

    private fun verifyData(){
        if(repository.getData().size > 0){
            main.onSnackNavigationBar(App.context.getString(R.string.regristro))
            nPlacaRegister.postValue("")
        }else{
            main.onSnackNavigationBar(App.context.getString(R.string.error_registro))
        }
    }

}