package com.prueba.hugo.utils.extensions

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
val realm = Realm.getDefaultInstance()
fun doTransaction(transaction: Realm.() -> Unit = {}) {
    try {
        realm.beginTransaction()
        realm.transaction()
    } catch (e: Exception) {
        println(e.message)
        realm.cancelTransaction()
    } finally {
        realm.commitTransaction()
    }
}

fun <T : RealmModel> T.getDataCarId(key: String): RealmResults<T> {
    lateinit var data: RealmResults<T>
    val dataClass = javaClass
    doTransaction { data = realm.where(dataClass).equalTo("id", key).findAll() }
    return data
}

fun <T : RealmModel> T.create() {
    try {
        realm.executeTransaction { it.copyToRealmOrUpdate(this) }
    } catch (e: Exception) {
        println(e.message)
        realm.cancelTransaction()
    }
}

fun  <T : RealmModel> T.getDataCar(): RealmResults<T> {
    lateinit var data: RealmResults<T>
    val dataClass = javaClass
     doTransaction { data = realm.where(dataClass).findAll() }
    return data
}

fun <T : RealmModel> T.deleteData(id:String) :Boolean {
    var delete = false
    lateinit var data: RealmResults<T>
    val dataClass = javaClass
    doTransaction {
        data = realm.where(dataClass).equalTo("id", id).findAll()
        data.deleteAllFromRealm()
        delete = true
    }
    return delete
}
