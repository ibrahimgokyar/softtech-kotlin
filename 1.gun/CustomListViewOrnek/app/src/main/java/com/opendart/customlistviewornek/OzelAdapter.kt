package com.opendart.customlistviewornek

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView

class OzelAdapter(private val context : Activity, private val title: Array<String>,
                  private val arrayDescription : Array<String>, private val arrayImage : Array<Int>)
    : ArrayAdapter<String>(context,R.layout.custom_row,title), ListAdapter {

    //gizli bir for döngüsü
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater  = context.layoutInflater
        val rowView  = inflater.inflate(R.layout.custom_row,null,true)
        val titleText = rowView.findViewById(R.id.title)as TextView
        val descriptionText = rowView.findViewById(R.id.description)as TextView
        val imgView = rowView.findViewById(R.id.icon) as ImageView //glide

        titleText.text = title[position]
        descriptionText.text = arrayDescription[position]
        imgView.setImageResource(arrayImage[position])

        return rowView
    }

}