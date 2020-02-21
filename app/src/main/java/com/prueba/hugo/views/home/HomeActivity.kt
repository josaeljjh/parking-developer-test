package com.prueba.hugo.views.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.prueba.hugo.R
import com.prueba.hugo.interfaces.InterfaceGlobal
import com.prueba.hugo.utils.Cons
import com.prueba.hugo.utils.extensions.*
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
class HomeActivity : AppCompatActivity(), InterfaceGlobal.mainGlobal {

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        config()
    }
    private fun config() {
        setTransparentStatusBar()
        master.marginUpdate()
        navigationBar.configBottom()
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(
            navigationBar,
              navController
          )
    }

    override fun onSnackNavigationBar(message: String) {
        launch {
            Cons.activity?.navigationBar.snackNavigationBar(message)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
