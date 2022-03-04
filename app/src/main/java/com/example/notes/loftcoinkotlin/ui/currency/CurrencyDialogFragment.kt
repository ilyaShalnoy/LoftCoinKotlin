package com.example.notes.loftcoinkotlin.ui.currency

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.data.currency.Currency
import com.example.notes.loftcoinkotlin.databinding.DialogCurrencyBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class CurrencyDialogFragment @Inject constructor(baseComponent: BaseComponent) : DialogFragment() {

    private lateinit var binding: DialogCurrencyBinding

    private val component = DaggerCurrencyComponent.builder()
        .baseComponent(baseComponent)
        .build()

    private lateinit var viewModel: CurrencyViewModel

    private lateinit var adapter: CurrencyDialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, component.viewModelFactory())[CurrencyViewModel::class.java]
        adapter = CurrencyDialogAdapter(object : CurrencyDialogAdapter.OnItemDialogClickListener {
            override fun onItemClick(currency: Currency) {
                viewModel.updateCurrencies(currency)
            }

        })
        viewModel.allCurrencies().observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogCurrencyBinding.inflate(requireActivity().layoutInflater)
        binding.recyclerDialog.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.recyclerDialog.adapter = adapter
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.choose_currency)
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        binding.recyclerDialog.adapter = null
        super.onDestroyView()
    }
}
