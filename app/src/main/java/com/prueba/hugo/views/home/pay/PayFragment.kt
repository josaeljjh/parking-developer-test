package com.prueba.hugo.views.home.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prueba.hugo.databinding.FragmentPayBinding
import com.prueba.hugo.utils.Cons.nPLACA
import com.prueba.hugo.utils.extensions.Gone
import com.prueba.hugo.utils.extensions.Visible
import kotlinx.android.synthetic.main.fragment_pay.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayFragment : Fragment() {

    private val viewModel by viewModel<PayViewModel>()
    private var payBinding: FragmentPayBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pay, container, false)
        return payBinding?.root ?: FragmentPayBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = this@PayFragment
            pay = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData(){
        arguments?.let {
            val placa = it.getString(nPLACA,"")
            buscarPago.Gone()
            nPlacaPay.text = placa
            containerPlaca.Visible()
            viewModel.calcularPago(placa)
        }
    }

}
