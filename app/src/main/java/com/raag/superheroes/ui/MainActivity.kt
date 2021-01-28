package com.raag.superheroes.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import com.raag.superheroes.databinding.ActivityMainBinding
import com.raag.superheroes.model.Hero
import com.raag.superheroes.ui.DetallesActivity.Companion.KEY_HEROES
import com.raag.superheroes.ui.DetallesActivity.Companion.KEY_IMAGE

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroImg: ImageView

    companion object{
        const val REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btGuardar.setOnClickListener {
            val name = binding.etNameHero.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating.toDouble()

            val hero = Hero(name, alterEgo, bio, power)
            saveHero(hero)
        }

        heroImg = binding.imgHero
        heroImg.setOnClickListener {
            openCamera()
        }

    }

    private fun openCamera() {
        val openCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(openCamera, REQUEST_CODE)
    }

    private fun saveHero(hero: Hero) {
        val intent = Intent(this, DetallesActivity::class.java)
        intent.putExtra(KEY_HEROES, hero)
        intent.putExtra(KEY_IMAGE, heroImg.drawable.toBitmap())
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            val extras = data?.extras
            val heroImage = extras!!.getParcelable<Bitmap>("data")
            heroImg.setImageBitmap(heroImage)
        } else{
            Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show()
        }
    }
}