package com.example.assignment_1github

import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val name=findViewById<TextView>(R.id.Name)
    val number=findViewById<TextView>(R.id.Number)
    val address=findViewById<TextView>(R.id.Address)
    val button_Save=findViewById<Button>(R.id.button)
    val listview=findViewById<ListView>(R.id.listview)
    val details= arrayOf("Name","Number","Address")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_Save.setOnClickListener {
            var name= name.text.toString()
            var number= number.text.toString()
            var address= address.text.toString()


            val  contactdetails = hashMapOf(
                "name" to name,
                "number" to number,
                "address" to address
            )
            db.collection("person")
                .add( contactdetails ).addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext,"${documentReference.id}",Toast.LENGTH_LONG).show()
                    // Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext,"$e",Toast.LENGTH_LONG).show()
                    // Log.w(TAG, "Error adding document", e)
                }
        }
//        val adaapter=ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,details)
//        listview.adapter=adaapter
//        listview.onItemClickListener=object :AdapterView.OnItemClickListener{
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                Toast.makeText(applicationContext,"clicked ttem="+details[position],Toast.LENGTH_LONG).show()
//            }
        }
    }
}