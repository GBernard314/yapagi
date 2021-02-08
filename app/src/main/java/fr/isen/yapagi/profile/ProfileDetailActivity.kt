package fr.isen.yapagi.profile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.squareup.picasso.Picasso
import fr.isen.yapagi.R
import fr.isen.yapagi.databinding.ProfileDetailBinding
import java.io.File


private lateinit var prefFile : SharedPreferences

class ProfileDetailActivity : AppCompatActivity() {
    private lateinit var binding: ProfileDetailBinding
    private var filePath: String = ""



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
        binding.profileDetailUserImg.setOnClickListener{
            val intent = Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 777)

            //saveUserProfile(getUserName(), getUserDescription(), "image_link")
        }
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



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 777) {
            if (isStoragePermissionGranted()){
                filePath = data?.data?.path.toString()
                val file = File(filePath)
                binding.profileDetailUserImg.setImageURI(Uri.fromFile(file));
            }
        }
    }
    private fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("test", "Permission is granted")
                true
            } else {
                Log.d("test", "Permission is revoked")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.d("test", "Permission is granted")
            true
        }
    }



    companion object {
        private const val PREFERENCES_FILE = "user_profile_preferences"

        //USER'S PICTURE//
        const val AVATAR_CHOICE_CODE = 111
        const val AVATAR_URL         = "url"

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