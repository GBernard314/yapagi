package fr.isen.yapagi.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afollestad.vvalidator.form
import fr.isen.yapagi.R
import fr.isen.yapagi.databinding.FragmentLoginBinding
import fr.isen.yapagi.network.Authentication

private lateinit var binding: FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var authenticator: Authentication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authenticator = Authentication(activity as Activity)

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        form {
            input(binding.etEmail) {
                isNotEmpty()
                length().atLeast(6)
                matches("^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\$")
            }

            input(binding.etPassword) {
                isNotEmpty()
                length().atLeast(8)
            }

            submitWith(binding.btnLogin) { result ->
                authenticator.loginUser(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                displayToast("Correctly Logged In")
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun displayToast(str: String) {
        Toast.makeText(activity?.applicationContext, str, Toast.LENGTH_SHORT).show();
    }
}

