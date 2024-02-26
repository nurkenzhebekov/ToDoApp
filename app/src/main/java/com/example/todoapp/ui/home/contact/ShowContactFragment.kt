package com.example.todoapp.ui.home.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentShowContactBinding

class ShowContactFragment : Fragment() {

    private lateinit var binding: FragmentShowContactBinding
    private val arguments: ShowContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowContactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            tvContactName.text = arguments.contactName
            tvMobileNumber.text = arguments.mobileNumber
            tvWorkNumber.text = arguments.workNumber
        }
    }
}