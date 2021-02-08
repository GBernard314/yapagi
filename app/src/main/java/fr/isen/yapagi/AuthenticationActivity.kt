package fr.isen.yapagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.yapagi.adapter.AuthenticationPagerActivity
import fr.isen.yapagi.data.User
import fr.isen.yapagi.databinding.ActivityAuthenticationBinding
import fr.isen.yapagi.network.Authentication
import fr.isen.yapagi.network.Database
import fr.isen.yapagi.network.UserDataListener


private lateinit var binding: ActivityAuthenticationBinding
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Authentication.getUserID() != null) {
            intent = Intent(this, FeedActivity::class.java)
            Authentication.getUserID()?.let { it1 ->
                Database.getUser(it1, object: UserDataListener {
                    override fun onSuccess(value: User?) {
                        intent.putExtra("username", value?.username)
                        startActivity(intent)
                    }
                })
            }

        }

        binding.viewPager.adapter = AuthenticationPagerActivity(this, supportFragmentManager)
    }
}