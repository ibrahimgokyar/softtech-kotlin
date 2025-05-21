package com.opendart.cameraornek3

import android.Manifest.permission.*
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var captureButton: Button
    lateinit var btnGallery: Button

    val REQUEST_IMAGE_CAPTURE = 1
    val IMAGE_CHOOSE = 1000
    val PERMISSION_CODE = 1001
    val PERMISSON_REQUEST_CODE: Int = 101

    private var mCurrentPhotoPath: String? = null

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
        captureButton = findViewById(R.id.btnCapture)

        captureButton.setOnClickListener {
            if (checkPermission()) {
                takePicture()
            } else {
                requestPermission()
            }
        }

        btnGallery.setOnClickListener {
            if (checkPermission()) {
                chooseImageFromGallery()
            } else {
                requestGalleryPermission()
            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_MEDIA_IMAGES, CAMERA),
                PERMISSON_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
                PERMISSON_REQUEST_CODE
            )
        }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file = createFile()
        val uri: Uri = FileProvider.getUriForFile(this, "com.opendart.android.fileprovider", file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                this,
                READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
        }
    }

    @Throws(IOException::class)
    private fun createFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            mCurrentPhotoPath = absolutePath
        }
    }

    private fun requestGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_MEDIA_IMAGES, CAMERA),
                PERMISSION_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
                PERMISSION_CODE
            )
        }
    }

    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                chooseImageFromGallery()
            } else {
                Toast.makeText(this, "Gerekli izinler verilmedi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK) {
            imageView.setImageURI(data?.data)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val file = File(mCurrentPhotoPath)
            val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
            imageView.setImageBitmap(bitmap)

            // Galeriye kaydediyoruz
            saveImageToGallery(file)
        }
    }

    private fun saveImageToGallery(file: File) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val contentResolver = contentResolver
        val uri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            val outputStream = contentResolver.openOutputStream(it)
            val inputStream = file.inputStream()
            inputStream.copyTo(outputStream!!)
            inputStream.close()
            outputStream.close()

            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            contentResolver.update(uri, contentValues, null, null)
        }
    }
}