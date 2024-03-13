package com.example.todoapp.auth

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSignInEmailBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SignInEmailFragment : Fragment() {

    private var _binding: FragmentSignInEmailBinding? = null
    private val binding get() = _binding!!

    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInEmailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        findNavController().navigate(R.id.action_signInEmailFragment_to_navigation_notes)
    }

    private fun setListeners() {
        binding.btSignUp.setOnClickListener {
            if (!validate()) return@setOnClickListener
            signUp(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }

        binding.btSignIn.setOnClickListener {
            if (!validate()) return@setOnClickListener
            signIn(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }

        binding.tvForgotPassword.setOnClickListener {
            resetPassword()
        }

        binding.btConfirmCode.setOnClickListener {
            confirmPasswordReset()
        }
    }

    private fun confirmPasswordReset() {
        val confirmCode = binding.edtConfirmCode.text
        val newPassword = binding.edtNewPassword.text

        if (confirmCode.isNullOrEmpty()) {
            binding.edtConfirmCode.error = "Enter confirm code"
            return
        }

        if (newPassword.isNullOrEmpty()) {
            binding.edtConfirmCode.error = "Enter new password"
            return
        }

        auth.confirmPasswordReset(confirmCode.toString(), newPassword.toString())
            .addOnFailureListener {
                Log.e("auth", "confirm failed", it.cause)
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                hideResetPassword()
            }
            .addOnSuccessListener {
                hideResetPassword()
            }
    }

    private fun resetPassword() {
        val email = binding.edtEmail.text

        if (email.isNullOrEmpty()) {
            binding.edtEmail.error = "Enter email"
            return
        }

        auth.sendPasswordResetEmail(email.toString())
            .addOnFailureListener{
                Log.e("auth", "reset failed", it.cause)
            }
            .addOnSuccessListener {
                showResetPassword()
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirm password reset")
                    .setMessage("We have sent you confirmation code to your " +
                            "email account $email. Enter confirm code")
                    .show()
            }
    }

    private fun validate() : Boolean {
        if (binding.edtEmail.text.isNullOrEmpty()) {
            binding.edtEmail.error = "enter email"
            return false
        }

        if (binding.edtPassword.text.isNullOrEmpty()) {
            binding.edtPassword.error = "enter password"
            return false
        }

        return true
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener{
                Log.e("auth", "signUp failed", it.cause)
                Toast.makeText(
                    requireContext(),
                    "Email or password are incorrect",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Welcome, ${it.user?.displayName ?: it.user?.email}!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener{
                Log.e("auth", "signIn failed", it.cause)
                Toast.makeText(
                    requireContext(),
                    "Email or password are incorrect",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Welcome back, ${it.user?.displayName ?: it.user?.email}!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideResetPassword() {
        binding.edtConfirmCode.visibility = View.GONE
        binding.btConfirmCode.visibility = View.GONE
        binding.edtNewPassword.visibility = View.GONE
    }

    private fun showResetPassword() {
        binding.edtConfirmCode.visibility = View.VISIBLE
        binding.btConfirmCode.visibility = View.VISIBLE
        binding.edtNewPassword.visibility = View.VISIBLE
    }
}