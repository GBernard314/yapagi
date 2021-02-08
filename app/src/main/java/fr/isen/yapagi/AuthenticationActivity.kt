package fr.isen.yapagi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.yapagi.adapter.AuthenticationPagerActivity
import fr.isen.yapagi.databinding.ActivityAuthenticationBinding
import fr.isen.yapagi.network.Authentication
import fr.isen.yapagi.network.Database


private lateinit var binding: ActivityAuthenticationBinding
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = AuthenticationPagerActivity(this, supportFragmentManager)
    }
}