package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import kotlin.random.Random


class FragmentOne : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        val randomId = Random.nextInt(0, 1432)
        val url = "https://narutodb.xyz/api/character/" + randomId;

            val rq: RequestQueue = Volley.newRequestQueue(context)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    Log.d("res", response.toString())
                    displayCharacter(response.toString(), view)
                },
                { error ->
                    Log.d("wrong?", error.toString())
                }
            )
            rq.add(jsonObjectRequest)
        return view;
    }

    fun displayCharacter(response : String, view : View?) {
        val jsonObject = JSONObject(response)
        val imageView = view?.findViewById<ImageView>(R.id.imageView);
        val nameText = view?.findViewById<TextView>(R.id.textName)
        val jutsuText = view?.findViewById<TextView>(R.id.textJutsu)

        nameText?.text = jsonObject.getString("name")
        Log.d("re", jsonObject.getString("name"))


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
        jutsuText?.text = stringBuilder
    }
}

