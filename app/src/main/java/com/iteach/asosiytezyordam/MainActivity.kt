package com.iteach.asosiytezyordam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val personCollectRef = Firebase.firestore.collection("persons")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveOrder(Order("","+998didiodo",System.currentTimeMillis(),0.0,0.0,0))
    }

    private fun saveOrder(person: Order) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectRef
                .add(person)
                .addOnSuccessListener {
                    Toast.makeText(this@MainActivity,"success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@MainActivity,it.message, Toast.LENGTH_LONG).show()
                }


        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@MainActivity,e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}