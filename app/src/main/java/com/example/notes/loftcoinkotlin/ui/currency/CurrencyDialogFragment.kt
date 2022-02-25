package com.example.notes.loftcoinkotlin.ui.currency

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.data.currency.CurrencyRepository
import com.example.notes.loftcoinkotlin.data.currency.CurrencyRepositoryImpl

import com.example.notes.loftcoinkotlin.databinding.DialogCurrencyBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CurrencyDialogFragment : DialogFragment() {

    private lateinit var binding: DialogCurrencyBinding

    private lateinit var currencyRepository: CurrencyRepository

    private lateinit var adapter: CurrencyDialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currencyRepository = CurrencyRepositoryImpl(requireContext())
        adapter = CurrencyDialogAdapter()
        currencyRepository.availableCurrencies().observe(this) {
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
