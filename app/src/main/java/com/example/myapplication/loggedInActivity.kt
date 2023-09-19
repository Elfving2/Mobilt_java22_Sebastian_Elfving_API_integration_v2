package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.Fragments.FragmentOne
import com.example.myapplication.Fragments.FragmentTwo

class loggedInActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)


        findViewById<Button>(R.id.randomButton).setOnClickListener {
            val fragment = FragmentOne() // Replace with your fragment's class name
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragmentContainer,
                fragment
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }

        findViewById<Button>(R.id.searchFavoriteButton).setOnClickListener {
            val fragment = FragmentTwo() // Replace with your fragment's class name
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragmentContainer,
                fragment
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
            // Create a JsonObjectRequest to retrieve a JSON object

        // fragments

    }