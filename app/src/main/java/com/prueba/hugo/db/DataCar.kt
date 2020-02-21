package com.prueba.hugo.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */


open class DataCar: RealmObject() {
    @PrimaryKey
    var id: String? = ""
    var user: String? = ""
    var timeStart: Long? = 0
    var timeEnd: Long? = 0
    var timeTotal:Int? = 0
}