package com.example.nfc_project

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Toast
import com.example.nfc_project.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTitle(SelectAcitivty.TITLE_MAIN)

        pendingIntent = PendingIntent
            .getActivity(
                this, 0,
                Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcCheck(nfcAdapter)) {
            if (!nfcAdapter.isEnabled) {
                Toast.makeText(this, "NFC esta desactivado.", Toast.LENGTH_SHORT).show()
                /**permite ir a opciones de NFC del celular**/
                intent = Intent(Settings.ACTION_NFC_SETTINGS)
                startActivity(intent)
            } else {
                Toast.makeText(this, "NFC esta activo", Toast.LENGTH_SHORT).show()
            }
        }

        binding.botonRecargar.setOnClickListener { recargar ->
            var intent = Intent(this, SelectAcitivty::class.java)
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        var tag = intent?.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        println(tag.toString())
        if (tag != null) {
            binding.textTag.text = "Tarjeta transporte No. = " + tag.toString()
            val data = NfcA.get(tag)
//            val datqa = data.atqa
//            binding.dataTag.text = data.toString() + "atqa = " +datqa
            //Toast.makeText(this, "EXTRA TAG:" + tag.toString(), Toast.LENGTH_SHORT).show()
        }

    }


    override fun onResume() {
        super.onResume()
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
        }
        var tag = intent?.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        println(tag.toString())
        if (tag != null) {
            binding.textTag.text = "Tarjeta SITP=" + tag.toString()
        }

    }

    override fun onPause() {
        super.onPause()
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this)
        }
    }

    private fun nfcCheck(nfcAdapter: NfcAdapter): Boolean {
        if (nfcAdapter == null) {
            Toast.makeText(this, "El Movil no tiene chip nfc", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }


}