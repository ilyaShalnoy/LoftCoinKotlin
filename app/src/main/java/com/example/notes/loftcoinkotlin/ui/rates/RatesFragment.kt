package com.example.notes.loftcoinkotlin.ui.rates

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.loftcoinkotlin.core.BaseComponent
import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.core.util.ChangeFormatter
import com.example.notes.loftcoinkotlin.core.util.PriceFormatter
import com.example.notes.loftcoinkotlin.databinding.FragmentRatesBinding
import com.example.notes.loftcoinkotlin.ui.BaseFragment
import javax.inject.Inject

class RatesFragment @Inject constructor(baseComponent: BaseComponent) : BaseFragment<FragmentRatesBinding>() {

    private lateinit var adapter: RatesAdapter

    private lateinit var viewModel: RatesViewModel

    private val component = DaggerRatesComponent.builder()
        .baseComponent(baseComponent)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RatesAdapter(PriceFormatter(), ChangeFormatter())
        viewModel = ViewModelProvider(this, component.viewModelFactory())[RatesViewModel::class.java]
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
        viewModel.coinsLiveData.observe(viewLifecycleOwner, adapter::submitList)

        viewModel.isRefreshing.observe(
            viewLifecycleOwner, binding.swipeRefresh::setRefreshing
        )

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_rates, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.currency_dialog == item.itemId) {
            NavHostFragment
                .findNavController(this)
                .navigate(R.id.currency_dialog)
            return true
        } else if (R.id.sorting == item.itemId) {
            viewModel.switchSortingOrder()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        binding.recyclerRatesList.swapAdapter(null, false)
        super.onDestroyView()
    }


}