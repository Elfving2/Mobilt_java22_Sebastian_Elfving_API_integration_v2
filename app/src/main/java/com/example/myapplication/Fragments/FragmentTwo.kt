package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class FragmentTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_two, container, false)

        val editText = view.findViewById<EditText>(R.id.editText)
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val textValue = editText.text
            val url = "https://www.narutodb.xyz/api/character/search?name=" + textValue

            val rq: RequestQueue = Volley.newRequestQueue(context)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    Log.d("res", response.toString())
                    getCharacter(response.toString(), view)
                },
                { error ->
                    Toast.makeText(context, "That character dosen´t exist!", Toast.LENGTH_LONG ).show()
                    Log.d("wrong?", error.toString())
                }
            )
            rq.add(jsonObjectRequest)
        }
        return view;
    }

    fun getCharacter(response: String, view : View) {
        val imageView = view.findViewById<ImageView>(R.id.imageView1)
        val nameText = view.findViewById<TextView>(R.id.textViewName)
        val jutsuText = view.findViewById<TextView>(R.id.textViewJutsu)

        val jsonObject = JSONObject(response)

        var imageArray = JSONArray()
        var jutsu = JSONArray()
        val stringBuilder = StringBuilder()

        try {
            jutsu = jsonObject.getJSONArray("jutsu")
            for (j in 0 until jutsu.length()) {
                Log.d("jutsus", jutsu[j].toString())
                stringBuilder.appendLine(jutsu[j])
            }
        } catch (e: JSONException) {
            stringBuilder.appendLine("has no jutsu")
        }
        var imageSrc: String?

        try {
            imageArray = jsonObject.getJSONArray("images")
            imageSrc = imageArray.get(0).toString()
        } catch (e: JSONException) {
            imageSrc = "https://w0.peakpx.com/wallpaper/865/294/HD-wallpaper-kakashi-dark-error.jpg"
        }


        Picasso.get().load(imageSrc).into(imageView)
        nameText?.text = jsonObject.getString("name")
        jutsuText.text = stringBuilder.toString()



    }
}