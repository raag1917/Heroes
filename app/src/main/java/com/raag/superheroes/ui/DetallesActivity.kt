package com.raag.superheroes.ui

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raag.superheroes.databinding.ActivityDetallesBinding
import com.raag.superheroes.model.Hero

class DetallesActivity : AppCompatActivity() {
    companion object{
        const val KEY_HEROES = "heroes"
        const val KEY_IMAGE = "image"
    }

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val hero = bundle.getParcelable<Hero>(KEY_HEROES)!!
        val img = bundle.getParcelable<Bitmap>(KEY_IMAGE)

        binding.imgHero.setImageBitmap(img)

        binding.tvNameHero.text = hero.name
        binding.tvAlterEgo.text = hero.alterEgo
        binding.tvBio.text = hero.bio
        binding.rbPower.rating = hero.power.toFloat()
    }
}