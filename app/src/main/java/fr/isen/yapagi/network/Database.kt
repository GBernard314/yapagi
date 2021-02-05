package fr.isen.yapagi.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.data.User

class Database {
    companion object{
        private const val TAG = "DATABASE"

        private const val URL = "https://yapagi-8c1ba-default-rtdb.europe-west1.firebasedatabase.app/"
        private const val USERS = "users"
        private const val POSTS = "posts"

        private val db = Firebase.database(URL)

        fun createPost(post: Post){
            val postsDb = db.getReference(POSTS)

            Log.d(TAG, "CREATING_POST...")
            val postId = postsDb.push().key
            postId?.let{
                postsDb.child(postId).setValue(post)
                Log.d(TAG, "POST_CREATED !")
            }?: run{
                Log.d(TAG, "COULD_NOT_CREATE_POST")
            }
        }

        fun updatePost(postId: String, post: Post){
            val postsDb = db.getReference(POSTS)
            Log.d(TAG, "UPDATING_POST_@$postId")
            postsDb.child(postId).child(postId).setValue(post)
            Log.d(TAG, "POST_UPDATED")
        }

        fun getPosts(){
            val postsDb = db.getReference(POSTS)
            
        }

        fun saveUser(userId: String, userFirstName: String, userLastName: String,
                     userUsername: String, userEmail: String){
            val usersDb = db.getReference(USERS)

            Log.d(TAG, "SAVING_USER...")
            val user = User(userFirstName, userLastName, userUsername, userEmail)
            usersDb.child(userId).setValue(user)
            Log.d(TAG, "USER_SAVED !")
        }

        fun getUser(userId: String, listener: UserDataListener){
            val usersDb = db.getReference(USERS)

            usersDb.child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "GETTING_USER_@$userId")
                    listener.onSuccess(dataSnapshot.getValue<User>())
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "COULD_NOT_GET_USER_@$userId")
                }
            })
        }
    }
}