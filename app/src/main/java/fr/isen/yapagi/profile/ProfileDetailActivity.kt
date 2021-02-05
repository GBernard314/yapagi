package fr.isen.yapagi.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.isen.yapagi.R
import fr.isen.yapagi.connection.LandingActivity
import fr.isen.yapagi.databinding.ProfileDetailBinding

private lateinit var prefFile : SharedPreferences

class ProfileDetailActivity : AppCompatActivity() {
    private lateinit var binding: ProfileDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Preferences file
        prefFile = this.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

        //If user owner = Save user profile
        //saveUserProfile("username", "userDescription", "userPicture")

        //User Login
        if(getUserName() != "none"){
            //BDD getUserName
            binding.profileDetailUserName.text = getUserName()
        }

        //User description
        if(getUserDescription()!= "none"){
            //BDD getDescription
            binding.profileDetailDescription.text = getUserDescription()
        }

        //User Picture
        //BDD getUserPicture
        Picasso.get().load(getUserPicture())
            .placeholder(R.drawable.avatar_0)
            .into(binding.profileDetailUserImg)
    }



    private fun saveUserProfile(userName: String, userDescription: String, userPicture: String){

        //1) Set editor for writing in
        val editor = prefFile.edit()

        //2) Write user profile's data
        editor.putString(USER_NAME, userName)
        editor.putString(USER_DESCRIPTION, userDescription)
        editor.putString(USER_PICTURE, userPicture)
        editor.commit()

        Log.d("test", "User profiles saved : $userName | $userDescription")
    } //Save user's profile for futur displaying
    fun clearUserData(){
        val editor = prefFile.edit()

        editor.clear()
        editor.commit()
    }                                                                         //Clear user's data in preferences file



    companion object {
        private const val PREFERENCES_FILE = "user_profile_preferences"

        //USER'S DATA//
        private const val USER_NAME        = "username"
        private const val USER_DESCRIPTION = "description"
        private const val USER_PICTURE     = "picture"

        //SETTER & GETTER//
        private fun getUserName() : String {
            return prefFile.getString(USER_NAME, "none").toString()
        }
        private fun getUserDescription() : String {
            return prefFile.getString(USER_DESCRIPTION, "none").toString()
        }
        private fun getUserPicture() : String {
            return prefFile.getString(USER_PICTURE, "none").toString()
        }
    }
}