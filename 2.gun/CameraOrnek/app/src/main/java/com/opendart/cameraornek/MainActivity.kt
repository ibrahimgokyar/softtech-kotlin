package com.opendart.cameraornek

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    lateinit var imageView : ImageView
    lateinit var  captureButton : Button
    lateinit var btnGallery: Button

    val REQUEST_IMAGE_CAPTURE = 1
    val IMAGE_CHOOSE = 1000
    val PERMISSION_CODE = 1001
    val PERMISSON_REQUEST_CODE : Int = 101
    private var  mCurrentPhotoPath : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btnGallery = findViewById(R.id.btnGallery)
        imageView = findViewById(R.id.image_view)


        btnGallery.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission()) {
                    chooseImageGallery()
                } else {
                    requestGalleryPermission()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {


        if(requestCode == PERMISSION_CODE) {
            if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)&&
                grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                chooseImageGallery()
            }
            else
            {
                Toast.makeText(this,"İzin Gerekli",Toast.LENGTH_SHORT).show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            var bitmap : Bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            imageView.setImageBitmap(bitmap)
        }
        else if(requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK){
            imageView.setImageURI(data?.data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun chooseImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_CHOOSE)
    }


    private fun checkPermission():Boolean {
        return(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,arrayOf(READ_EXTERNAL_STORAGE,CAMERA),PERMISSON_REQUEST_CODE)
    }


    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(this,arrayOf(READ_EXTERNAL_STORAGE,CAMERA),PERMISSION_CODE)
    }

}