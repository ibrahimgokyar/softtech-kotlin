package com.opendart.menuornek

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.util.Log


class MainActivity : AppCompatActivity() {
    lateinit var btnPopUpMenuGroup : Button
    lateinit var btnPopUpMenu : Button
    lateinit var btnContextMenu : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
           v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
     //context Menu
        btnContextMenu = findViewById(R.id.btnContextMenu)

        registerForContextMenu(btnContextMenu)

        btnContextMenu.setOnLongClickListener {
            openContextMenu(btnContextMenu)
            true
        }


        btnPopUpMenu = findViewById(R.id.btnPopUpMenu)
        btnPopUpMenu.setOnClickListener {

            val popup = PopupMenu(this@MainActivity, btnPopUpMenu)
            //Inflating the Popup using xml file
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)


            popup.setOnMenuItemClickListener({
                if (it.itemId == R.id.one) {
                    Toast.makeText(applicationContext, "One", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, it.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popup.show()

        }

        btnPopUpMenuGroup = findViewById(R.id.btnPopUpMenuGroup)

        btnPopUpMenuGroup.setOnClickListener {

            val popup = PopupMenu(this@MainActivity, btnPopUpMenu)
            //Inflating the Popup using xml file
            popup.menuInflater.inflate(R.menu.popup_menu_group, popup.menu)


            popup.setOnMenuItemClickListener({
                if (it.itemId == R.id.four && it.isChecked) {
                    Toast.makeText(applicationContext, "Four. Was Checked", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, it.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popup.show()

        }


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Context Menu")
        menu?.add(0, v?.id!!, 0, "Call")
        menu?.add(0, v?.id!!, 1, "SMS")
        menu?.add(1, v?.id!!, 0, "Search")

    }



    override fun onContextItemSelected(item: MenuItem): Boolean {

        when {
            item?.title == "Call" -> {
                Toast.makeText(applicationContext, "Call", Toast.LENGTH_LONG).show()
                return true
            }
            item?.title == "SMS" -> {
                Toast.makeText(applicationContext, "SMS", Toast.LENGTH_LONG).show()
                return true
            }
            item?.title == "Search" -> {
                Toast.makeText(applicationContext, "Search", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.add -> {
                Toast.makeText(applicationContext, "Add Tıklandı", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.call -> {
                Toast.makeText(applicationContext, "Call Tıklandı", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.day -> {
                Toast.makeText(applicationContext, "Day Tıklandı", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.compass -> {
                Toast.makeText(applicationContext, "Compass Tıklandı", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.agenda -> {
                Toast.makeText(applicationContext, "Agenda Tıklandı", Toast.LENGTH_LONG).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}