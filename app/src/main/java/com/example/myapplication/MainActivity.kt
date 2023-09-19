package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Fragments.Login
import com.example.myapplication.Fragments.Register


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button1).setOnClickListener {
            val fragment = Register() // Replace with your fragment's class name
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment) // R.id.fragment_container is the ID of the container view in your activity's layout
            transaction.addToBackStack(null) // If you want to add this transaction to the back stack
            transaction.commit()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val fragment = Login() // Replace with your fragment's class name
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment) // R.id.fragment_container is the ID of the container view in your activity's layout
            transaction.addToBackStack(null) // If you want to add this transaction to the back stack
            transaction.commit()
        }
    }
}