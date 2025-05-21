package com.opendart.kontaklistesiornek


import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.opendart.kontaklistesiornek.adapter.ContactsAdapter
import com.opendart.kontaklistesiornek.databinding.ActivityMainBinding
import com.opendart.kontaklistesiornek.util.hasPermission
import com.opendart.kontaklistesiornek.util.requestPermissionWithRationale
import com.opendart.kontaklistesiornek.viewmodels.ContactsViewModel

import android.Manifest
import android.content.pm.PackageManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val contactsViewModel by viewModels<ContactsViewModel>()
    private val CONTACTS_READ_REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       init()
    }


    private fun init() {
        binding.tvDefault.text = "Fetching contacts!!!"
        val adapter = ContactsAdapter(this)
        binding.rvContacts.adapter = adapter

        contactsViewModel.contactsLiveData.observe(this, Observer {
            binding.tvDefault.visibility = View.GONE
            adapter.contacts = it
        })

        if (hasPermission(Manifest.permission.READ_CONTACTS)) {
            contactsViewModel.fetchContacts()
        } else {
            requestPermissionWithRationale(
                Manifest.permission.READ_CONTACTS,
                CONTACTS_READ_REQ_CODE,
                getString(R.string.contact_permission_rationale)
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_READ_REQ_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            contactsViewModel.fetchContacts()
        }
    }
}