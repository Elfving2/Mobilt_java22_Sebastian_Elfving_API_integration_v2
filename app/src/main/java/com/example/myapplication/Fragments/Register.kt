package com.example.myapplication.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class Register : Fragment() {

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val requestQueue = Volley.newRequestQueue(activity)

        // change 192.168.39.147 depending on your own ipv4 address cmd -> ipconfig -> ipv4

        val url = "http://192.168.39.147:8080/databaseConnection/insertData.php"
        val usernameTextView = view.findViewById<EditText>(R.id.userName)
        val passwordTextView = view.findViewById<EditText>(R.id.password)


        val registerButton = view.findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {

            val getUsername = usernameTextView.text.toString().trim()
            val getPassword = passwordTextView.text.toString().trim()

            Log.d("username", getUsername)
            Log.d("pass", getPassword)


            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    val responseData = response.toString()
                    Log.d("SUCESS", responseData)
                    usernameTextView.setText("")
                    passwordTextView.setText("")
                    Toast.makeText(context, "You successfully created an account!",Toast.LENGTH_LONG ).show()
                },
                Response.ErrorListener { error ->
                    // Handle errors
                    Toast.makeText(context, "Something Went Wrong!",Toast.LENGTH_LONG ).show()
                    error.printStackTrace()
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = getUsername
                    params["password"] = getPassword
                    return params
                }
            }


// Add the request to the RequestQueue
            requestQueue.add(stringRequest)

        }







        return view
    }

}