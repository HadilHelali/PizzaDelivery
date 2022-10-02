package com.gl4.pizzadelivery

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    lateinit var nom: EditText
    lateinit var prenom: EditText
    lateinit var adresse: EditText
    lateinit var spinner: Spinner
    var toppings : MutableMap<String,Int> =
        mapOf("Tomato Sauce" to 0 , "Cheese" to 0 , "Pepper" to 0 , "Mushroom" to 0) as MutableMap<String, Int>


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nom = findViewById(R.id.editTextNom)
        prenom = findViewById(R.id.editTextTextPrenom)
        adresse = findViewById(R.id.editTextAdresse)

        val top1 = findViewById<Switch>(R.id.Tomato)
        top1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toppings["Tomato sauce"] = 1
            } else {toppings["Tomato sauce"] = 0}
        }
        val top2 = findViewById<Switch>(R.id.Fromage)
        top2?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toppings["Cheese"] = 1
            }else{toppings["Cheese"] = 0}
        }
        val top3 = findViewById<Switch>(R.id.poivre)
        top3?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toppings["Pepper"] = 1
            }else{toppings["Pepper"] = 0}
        }
        val top4 = findViewById<Switch>(R.id.champignon)
        top4?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toppings["Mushroom"] = 1
            }else{toppings["Mushroom"] = 0}
        }

        // Spinner //
        val pizzasize = resources.getStringArray(R.array.PizzaSize)
        spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, pizzasize
        )
        spinner.adapter = adapter
        // Spinner //

    }

    fun onDelivery(view: View) {
        var topp : String = ""
        for(top in toppings){
            if (top.value == 1){
                topp += top.key + " , "
            }
        }
        var command = "name : "+ nom.text.toString() +"\nsurname : "+ prenom.text.toString() +"\naddress : "+
                adresse.text.toString()+"\norder : "+ spinner.selectedItem.toString() +" Pizza with the following toppings : "+
                topp

        val mIntent = Intent(Intent.ACTION_SEND)
        //sens message
        mIntent.putExtra("address" , "sms"+50572950)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("vendeur@gmail.com"))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Your command")
        mIntent.putExtra(Intent.EXTRA_TEXT, command)


        startActivity(Intent.createChooser(mIntent, "Choose Email ..."))

    }

    override fun onRestart() {
        super.onRestart()
        val intent = Intent(this, SplashScreen::class.java)
        startActivity(intent)
    }

}