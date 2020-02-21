package com.prueba.hugo.views.home.car_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prueba.hugo.databinding.FragmentCarRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarRegisterFragment : Fragment() {

    private val viewModel by viewModel<CarRegisterViewModel>()
    private var carRegisterBinding: FragmentCarRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_car_register, container, false)
        return carRegisterBinding?.root ?: FragmentCarRegisterBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = this@CarRegisterFragment
            carregister = viewModel
        }.root

    }

}
