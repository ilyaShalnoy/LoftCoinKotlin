package com.example.notes.loftcoinkotlin.ui.rates

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.loftcoinkotlin.LoftApp
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.databinding.FragmentRatesBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
import timber.log.Timber

class RatesFragment : BaseFragment<FragmentRatesBinding>() {

    private lateinit var adapter: RatesAdapter

    private lateinit var viewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RatesAdapter()
        viewModel = ViewModelProvider(this, (requireActivity().application as LoftApp).ratesViewModelFactory)[RatesViewModel::class.java]
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRatesBinding {
        return FragmentRatesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.recyclerRatesList.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerRatesList.swapAdapter(adapter, false)
        binding.recyclerRatesList.setHasFixedSize(true)
        viewModel.refresh()
        viewModel.coinsLiveData.observe(viewLifecycleOwner) { coins ->
            adapter.submitList(coins)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_rates, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.d(item.toString())
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        binding.recyclerRatesList.swapAdapter(null, false)
        super.onDestroyView()
    }


}