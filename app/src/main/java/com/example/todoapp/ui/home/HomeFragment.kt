package com.example.todoapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private var contactName: String? = null
    private var mobileNumber: String? = null
    private var workNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOpen.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToContactFragment(
                    "Test Name",
                    "+996 703 267 047",
                    "+996 553 711 999"
                )
            )
        }

        binding.btAdd.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToAddContactFragment(
                    contactName.toString(),
                    mobileNumber.toString(),
                    workNumber.toString()
                )
            )
        }

        setFragmentResultListener(requestKey = "AddContactFragmentResult") { key, result ->
            contactName = result.getString("contactName")
            mobileNumber = result.getString("mobileNumber")
            workNumber = result.getString("workNumber")
        }

        binding.btShow.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToShowContactFragment(
                    contactName.toString(),
                    mobileNumber.toString(),
                    workNumber.toString()
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}