package com.example.notes.loftcoinkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.notes.loftcoinkotlin.databinding.FragmentWalletsBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    val binding get() = _binding!!

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}