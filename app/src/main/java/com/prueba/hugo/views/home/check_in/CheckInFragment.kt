package com.prueba.hugo.views.home.check_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prueba.hugo.databinding.FragmentCheckInBinding
import com.prueba.hugo.utils.Cons
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Josael Hernandez on 2020-02-20.
 * Contact : josaeljjh@gmail.com
 */
class CheckInFragment : Fragment() {

    private val viewModel by viewModel<CheckInViewModel>()
    private var checkInBinding: FragmentCheckInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_check_in, container, false)
        return checkInBinding?.root ?: FragmentCheckInBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CheckInFragment
            checkin = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        config()
    }

    private fun config() {
        Cons.activity = activity
    }
}
