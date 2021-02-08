package fr.isen.yapagi

import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.yapagi.network.Authentication

open class BaseActivity() : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val cartMenuView = menu?.findItem(R.id.settings)?.actionView

        val Auth = Authentication(this)

        if (cartMenuView != null) {
            cartMenuView.setOnClickListener {
                Auth.logoutUser()
                startActivity(Intent(this, AuthenticationActivity::class.java))
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}

