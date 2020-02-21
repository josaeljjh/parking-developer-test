package com.prueba.hugo.views.init

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.prueba.hugo.R
import com.prueba.hugo.databinding.ActivitySplashBinding
import com.prueba.hugo.utils.extensions.launchActivity
import com.prueba.hugo.views.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initDataBinding()
        observerStart()
    }

    private fun initDataBinding() {
        DataBindingUtil.setContentView<ActivitySplashBinding>(
            this,
            R.layout.activity_splash
        ).apply {
            lifecycleOwner = this@SplashActivity
            splash = viewModel
        }
    }

    private fun observerStart() {
        viewModel.startHome.observe(this, Observer { home ->
            if (home) {
                try {
                    launchActivity<HomeActivity>(true)
                } catch (e: Exception) {}
            }
        })
    }
}
