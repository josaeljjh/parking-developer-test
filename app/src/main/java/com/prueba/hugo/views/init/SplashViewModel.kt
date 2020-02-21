package com.prueba.hugo.views.init

import androidx.lifecycle.ViewModel
import com.prueba.hugo.R
import com.prueba.hugo.utils.SingleLiveEvent

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */

class SplashViewModel: ViewModel() {

    var imageRes = R.drawable.logo
    var startHome = SingleLiveEvent<Boolean>()
    fun onclickContinuar(){
        startHome.postValue(true)
    }
}