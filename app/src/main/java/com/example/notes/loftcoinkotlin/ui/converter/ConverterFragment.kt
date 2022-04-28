package com.example.notes.loftcoinkotlin.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.databinding.FragmentConverterBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ConverterFragment @Inject constructor(baseComponent: BaseComponent) : BaseFragment<FragmentConverterBinding>() {

    private val disposable = CompositeDisposable()

    private val component = DaggerConverterComponent.builder()
        .baseComponent(baseComponent)
        .build()

    private lateinit var viewModel: ConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment(), component.viewModelFactory())[ConverterViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController =  NavHostFragment.findNavController(this)
        disposable.add(binding.fromCoin.clicks().subscribe {
            val bundle = Bundle()
            bundle.putInt(KEY_MODE, MODE_FROM)
            navController.navigate(R.id.coins_sheet, bundle)
        })

        disposable.add(binding.toCoin.clicks().subscribe {
            val bundle = Bundle()
            bundle.putInt(KEY_MODE, MODE_TO)
            navController.navigate(R.id.coins_sheet, bundle)
        })
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentConverterBinding {
        return FragmentConverterBinding.inflate(inflater, container, false)
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }

}