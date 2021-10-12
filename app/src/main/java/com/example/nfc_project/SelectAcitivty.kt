package com.example.nfc_project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.nfc_project.databinding.ActivitySelectBinding

class SelectAcitivty : AppCompatActivity() {
    companion object {
        val PSE_URL = "https://registro.pse.com.co/PSEUserRegister/"
        val CREDIT_URL = "https://sucursalpersonas.transaccionesbancolombia.com/mua/USER?scis=dUliKD8E8GbdAqY18hVK786%2BTbOPMis15pvbrJETHC0%3D#no-back-button"
        val TITLE_MAIN ="Wellcome!"
        val TITLE_SECOND = "Choise your Pay"
    }
    private lateinit var binding : ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTitle(TITLE_SECOND)
                                                                                                    12
        binding.buttomPse.setOnClickListener { pse ->

            val uri = Uri.parse(PSE_URL)
            Log.d("Detail Acitivy","url: ${uri}")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }

        binding.buttomCredit.setOnClickListener { credit ->

            val uri = Uri.parse(CREDIT_URL)
            Log.d("Detail Acitivy","url: ${uri}")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }

    }
}