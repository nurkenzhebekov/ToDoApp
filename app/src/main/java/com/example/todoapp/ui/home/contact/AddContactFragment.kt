package com.example.todoapp.ui.home.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment() {

    private lateinit var binding: FragmentAddContactBinding
    private val arguments: AddContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.run {
            edtContactName.setText(arguments.contactName)
            edtMobileNumber.setText(arguments.mobileNumber)
            edtWorkNumber.setText(arguments.workNumber)
        }*/

        binding.btSaveContact.setOnClickListener {
            val contactName = binding.edtContactName.text.toString()
            val mobileNumber = binding.edtMobileNumber.text.toString()
            val workNumber = binding.edtWorkNumber.text.toString()
            setFragmentResult(
                requestKey = "AddContactFragmentResult",
                result = bundleOf(
                    "contactName" to contactName,
                    "mobileNumber" to mobileNumber,
                    "workNumber" to workNumber
                )
            )
        }
    }
}