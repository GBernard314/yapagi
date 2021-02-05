package fr.isen.yapagi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.yapagi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}