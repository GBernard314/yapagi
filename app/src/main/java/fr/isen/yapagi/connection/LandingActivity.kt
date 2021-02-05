package fr.isen.yapagi.connection

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.yapagi.databinding.LandingPageBinding

private lateinit var prefFile :  SharedPreferences

class LandingActivity : AppCompatActivity() {
    lateinit var binding : LandingPageBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Preferences file
        prefFile = this.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

        //BTN sign in
        binding.landingBtnSignIn.setOnClickListener{
            if(isRegisterIdentificationExisting()){ //Data Found
                //Social network page redirection
                Log.d("test", "User already connected as : " + getUserLogin() + " -> Social page redirection")
            }
            else{                                   //No data found
                //Sin in page redirection
                Log.d("test", "No data found -> Sign in redirection")
            }
        }

        //BTN sign up
        binding.landingBtnSignUp.setOnClickListener{
            //Sign up page redirection
            Log.d("test", "Sign up redirection")
        }
    }



    private fun isRegisterIdentificationExisting() : Boolean {
        return (getUserLogin() != "none" && getUserPassword() != "none")
        Log.d("test", "Data Found : " + getUserLogin() + " | " + getUserPassword())
    } //Check if user has already data connection saved
    fun saveUserData(userLogin: String, userPassword: String){

        //1) Set editor for writing in
        val editor = prefFile.edit()

        //2) Write user data
        editor.putString(USER_LOGIN, userLogin)
        editor.putString(USER_PASSWORD, userPassword)
        editor.commit()

        Log.d("test", "User saves as : $userLogin")
    } //Save user's data for futur connection
    fun clearUserData(){
        val editor = prefFile.edit()

        editor.clear()
        editor.commit()
    }                                       //Clear user's data in preferences file



    companion object {
        private const val PREFERENCES_FILE = "user_preferences"

        //USER'S DATA//
        private const val USER_LOGIN    = "login"
        private const val USER_PASSWORD = "password"

        //SETTER & GETTER//
        fun getUserLogin() : String {
            return prefFile.getString(USER_LOGIN, "none").toString()
        }
        private fun getUserPassword() : String {
            return prefFile.getString(USER_PASSWORD, "none").toString()
        }
    }
}