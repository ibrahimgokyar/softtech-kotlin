package com.opendart.kontaklistesiornek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opendart.kontaklistesiornek.R
import com.opendart.kontaklistesiornek.databinding.RowContactBinding
import com.opendart.kontaklistesiornek.databinding.RowContactDataBinding
import com.opendart.kontaklistesiornek.model.Contact


class ContactsAdapter(context: Context) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    var contacts = ArrayList<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowContactBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contacts[position]

        // Ana satırın binding nesnesi ile view'lara erişiyoruz
        with(holder.binding) {
            tvContactName.text = contact.name

            llContactDetails.removeAllViews()

            // Telefon numaralarını tek tek RowContactDataBinding kullanarak ekliyoruz
            contact.numbers.forEach {
                val detailBinding = RowContactDataBinding.inflate(layoutInflater, llContactDetails, false)
                detailBinding.imgIcon.setImageResource(R.drawable.ic_baseline_phone_24)
                detailBinding.tvContactData.text = it
                llContactDetails.addView(detailBinding.root)
            }

            // Email adreslerini tek tek RowContactDataBinding kullanarak ekliyoruz
            contact.emails.forEach {
                val detailBinding = RowContactDataBinding.inflate(layoutInflater, llContactDetails, false)
                detailBinding.imgIcon.setImageResource(R.drawable.ic_baseline_email_24)
                detailBinding.tvContactData.text = it
                llContactDetails.addView(detailBinding.root)
            }
        }
    }

    inner class MyViewHolder(val binding: RowContactBinding) : RecyclerView.ViewHolder(binding.root)
}
