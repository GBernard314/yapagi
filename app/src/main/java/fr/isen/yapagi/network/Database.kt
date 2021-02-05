package fr.isen.yapagi.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.yapagi.data.User

class Database {
    companion object{
        private const val TAG = "DATABASE"

        private const val URL = "https://yapagi-8c1ba-default-rtdb.europe-west1.firebasedatabase.app/"
        private const val USERS = "users"

        private val db = Firebase.database(URL)

        fun saveUser(userId: String, userFirstName: String, userLastName: String,
                     userUsername: String, userEmail: String){
            val usersDb = db.getReference(USERS)

            Log.d(TAG, "SAVING_USER...")
            val user = User(userFirstName, userLastName, userUsername, userEmail)
            usersDb.child(userId).setValue(user)
            Log.d(TAG, "USER_SAVED !")
        }
    }
}