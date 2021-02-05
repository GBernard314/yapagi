package fr.isen.yapagi.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Database {
    companion object{
        const val URL = "https://yapagi-8c1ba-default-rtdb.europe-west1.firebasedatabase.app/"
        private const val TAG = "DATABASE"
    }
    private val db = Firebase.database(URL)

    fun write(msg: String){
        db.getReference("message").setValue(msg)
    }

    fun read(refName: String){
        db.getReference(refName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}