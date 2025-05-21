package com.opendart.fragmentornek2


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView


class Fragment1 : Fragment() {


    private var editText: EditText? = null
    private var fragment2TextView: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragment2TextView =  activity?.findViewById(R.id.fragment2TextView)
        editText = activity?.findViewById(R.id.fragmentEditText) ?: null
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                fragment2TextView?.text = p0

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }


}