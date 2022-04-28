package com.example.notes.loftcoinkotlin.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.databinding.DialogCurrencyBinding
import com.example.notes.loftcoinkotlin.databinding.LiSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

const val KEY_MODE = "mode"
const val MODE_FROM = 1
const val MODE_TO = 2

class CoinsSheet @Inject constructor(baseComponent: BaseComponent) : BottomSheetDialogFragment() {

    private val component = DaggerConverterComponent.builder()
        .baseComponent(baseComponent)
        .build()

    private lateinit var binding: DialogCurrencyBinding

    private lateinit var viewModel: ConverterViewModel

    private lateinit var adapter: CoinsSheetAdapter

    private val disposable = CompositeDisposable()

    private var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment(), component.viewModelFactory())[ConverterViewModel::class.java]
        adapter = component.coinsSheetAdapter()
        mode = requireArguments().getInt("mode")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DialogCurrencyBinding.bind(view)

        binding.recyclerDialog.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerDialog.adapter = adapter

        disposable.add(viewModel.topCoins().subscribe(adapter::submitList))
    }

    override fun onDestroyView() {
        binding.recyclerDialog.adapter = null
        disposable.dispose()
        super.onDestroyView()
    }

}