package com.opendart.broadcastreceiverornek

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {
    private var mSnackBar: Snackbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val receiver = AirplaneModeReceiver {
            if(it){
                val messageToUser = "Airplane mod açık!"
                mSnackBar = Snackbar.make(findViewById(R.id.main), messageToUser,
                    Snackbar.LENGTH_LONG)
                mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
                mSnackBar?.show()
            }else {
                mSnackBar?.dismiss()
            }
        }
        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
    }
}