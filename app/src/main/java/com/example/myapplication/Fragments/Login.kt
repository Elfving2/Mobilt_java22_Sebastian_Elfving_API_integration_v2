package com.example.myapplication.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.MainActivity
import com.example.myapplication.Model.User
import com.example.myapplication.R
import com.example.myapplication.loggedInActivity
import kotlinx.serialization.json.JsonPrimitive
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException

class Login : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val url = "http://192.168.39.147:8080/databaseConnection/login.php"

        val loginButton = view.findViewById<Button>(R.id.loginButton)

        val usernameTextView = view.findViewById<EditText>(R.id.username)
        val passwordTextView = view.findViewById<EditText>(R.id.pass)

        val user = User()

        loginButton.setOnClickListener {
            val requestQueue = Volley.newRequestQueue(activity)
            val getUsername = usernameTextView.text.toString().trim()
            val getPassword = passwordTextView.text.toString().trim()

                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)
                            Log.d("e", response.toString());
                            user.setUsername(jsonObject.getString("username"))
                            user.setPassword(jsonObject.getString("password"))
                            user.setEmail(jsonObject.getString("email"))

                            Log.d("username", user.getUsername().toString())
                            Log.d("password", user.getPassword().toString())
                            Log.d("email", user.getEmail().toString())

                            val intent = Intent(context, loggedInActivity::class.java)
                            startActivity(intent)
                        } catch(e : JSONException) {
                            usernameTextView.setText("")
                            passwordTextView.setText("")
                            Toast.makeText(context, "Wrong User Name or Password!!",Toast.LENGTH_LONG ).show()
                        }


                        // send to another activity

                    },
                    Response.ErrorListener { error ->
                        // Handle errors
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

                // Add the request to the request queue
                requestQueue.add(stringRequest)
        }



        return view;
    }

}

