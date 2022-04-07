package com.example.notes.loftcoinkotlin.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.databinding.FragmentConverterBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
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

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentConverterBinding {
        return FragmentConverterBinding.inflate(inflater, container, false)
    }


}