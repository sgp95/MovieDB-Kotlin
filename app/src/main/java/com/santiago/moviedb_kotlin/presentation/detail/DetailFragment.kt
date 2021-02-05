package com.santiago.moviedb_kotlin.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.santiago.moviedb_kotlin.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val type = it.getInt("itemType")
            val id = it.getInt("itemId")
            binding.movieTitle.text = "DATA DE PRUEBA"
            binding.movieDescription.text = "TYPE: $type ITEM ID: $id"
        }
    }

}