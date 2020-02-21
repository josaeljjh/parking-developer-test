package com.prueba.hugo.views.home.check_out

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.prueba.hugo.R
import com.prueba.hugo.databinding.FragmentCheckOutBinding
import com.prueba.hugo.utils.Cons.nPLACA
import com.prueba.hugo.utils.extensions.gotoNavigate
import com.prueba.hugo.views.home.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_check_out.master
import org.koin.androidx.viewmodel.ext.android.viewModel


class CheckOutFragment : Fragment() {

    private val viewModel by viewModel<CheckOutViewModel>()
    private var checkOutBinding: FragmentCheckOutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_check_out, container, false)
        return checkOutBinding?.root ?: FragmentCheckOutBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = this@CheckOutFragment
            checkout = viewModel
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerPay()
    }

    private fun observerPay(){
        viewModel.launchPay.observe(viewLifecycleOwner, Observer {
            it?.let {
                val bundle = Bundle()
                bundle.putString(nPLACA,it)
                master.gotoNavigate(R.id.car_pay,bundle)
                (context as HomeActivity).navigationBar?.menu?.findItem(R.id.action_car_pay)?.isChecked = true
            }
        })
    }
}
