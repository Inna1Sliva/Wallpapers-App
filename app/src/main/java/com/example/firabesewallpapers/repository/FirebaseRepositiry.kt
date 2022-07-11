package com.example.firabesewallpapers.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.util.logging.LogManager

class FirebaseRepositiry {

    private  val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private  var lastVisible: DocumentSnapshot? = null
    private val pageSize: Long = 6

    fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
    fun queryWallpapers(): Task<QuerySnapshot> {
        if (lastVisible == null){
          return firebaseFirestore.collection("firabesewallpapers")
              .orderBy("data", Query.Direction.DESCENDING)
              .limit(9).get()
        }else{
            return firebaseFirestore.collection("firabesewallpapers")
                .orderBy("data", Query.Direction.DESCENDING)
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()

        }
    }
}