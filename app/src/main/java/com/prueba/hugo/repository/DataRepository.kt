package com.prueba.hugo.repository

import com.prueba.hugo.db.DataCar
import com.prueba.hugo.utils.extensions.create
import com.prueba.hugo.utils.extensions.deleteData
import com.prueba.hugo.utils.extensions.getDataCar
import com.prueba.hugo.utils.extensions.getDataCarId
import io.realm.RealmResults

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

class DataRepository() {
    //create data
    fun createData(dataCar: DataCar) {
        dataCar.create()
    }
    //get data
    fun getData( ): RealmResults<DataCar> {
        return DataCar().getDataCar()
    }
    //get data id
    fun getDataId(id:String): RealmResults<DataCar> {
        return DataCar().getDataCarId(id)
    }
    //delete data
    fun deleteDataId(id:String):Boolean{
        return DataCar().deleteData(id)
    }
}