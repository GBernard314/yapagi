package fr.isen.yapagi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import fr.isen.yapagi.databinding.PostBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: PostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        var like_icon = findViewById<LottieAnimationView>(R.id.like)
        like_icon.setOnClickListener(View.OnClickListener() {
            like_icon.playAnimation();
        })
    }
}