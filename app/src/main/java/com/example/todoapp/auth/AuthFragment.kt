package com.example.todoapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val auth = Firebase.auth

    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .build()
    }

    private val mGoogleSignInClient by lazy {
        GoogleSignIn.getClient(requireActivity(), gso)
    }

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (auth.currentUser != null) {
            updateUI()
        }

        binding.btSignInGoogle.setOnClickListener {
            signIn()
        }

        binding.btSignUpEmail.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_signInEmailFragment)
        }
    }

    private fun signIn() {
        googleSignInLauncher.launch(mGoogleSignInClient.signInIntent)
        findNavController().navigate(R.id.action_authFragment_to_navigation_notes)
    }

    private fun firebaseAuthWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnFailureListener {
                Log.e("auth", "signIn failde", it.cause)
            }
            .addOnSuccessListener {
                updateUI()
            }
    }

    private fun updateUI() {
        binding.tvUserName.text = "Hello, ${auth.currentUser?.displayName}"
        binding.tvUserName.visibility = View.VISIBLE
        findNavController().navigate(R.id.action_authFragment_to_navigation_notes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}