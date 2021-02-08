package fr.isen.yapagi.connection

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.yapagi.AuthenticationActivity
import fr.isen.yapagi.FeedActivity
import fr.isen.yapagi.data.User
import fr.isen.yapagi.databinding.LandingPageBinding
import fr.isen.yapagi.network.Authentication
import fr.isen.yapagi.network.Database
import fr.isen.yapagi.network.UserDataListener

private lateinit var prefFile: SharedPreferences

class LandingActivity : AppCompatActivity() {
    lateinit var binding: LandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefFile = this.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

        binding.landingBtnSignIn.setOnClickListener {
            if (isRegisterIdentificationExisting()) {
                intent = Intent(this, FeedActivity::class.java)
                Authentication.getUserID()?.let { it1 ->
                    Database.getUser(it1, object: UserDataListener{
                        override fun onSuccess(value: User?) {
                            intent.putExtra("username", value?.username)
                            startActivity(intent)
                        }
                    })
                }

            } else {
                intent = Intent(this, AuthenticationActivity::class.java)
                startActivity(intent)
            }
        }
        binding.landingBtnSignUp.setOnClickListener {
            intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
        }
    }


    private fun isRegisterIdentificationExisting(): Boolean {
        return Authentication.getUserID() != null
    }

    fun clearUserData() {
        val editor = prefFile.edit()
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val PREFERENCES_FILE = "app_prefs"
        private const val USER_LOGIN = "username"
        fun getUserLogin(): String {
            return prefFile.getString(USER_LOGIN, "none").toString()
        }

    }
}