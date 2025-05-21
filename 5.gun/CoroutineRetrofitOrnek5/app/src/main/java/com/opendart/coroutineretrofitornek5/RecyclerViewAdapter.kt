package com.opendart.coroutineretrofitornek5

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opendart.coroutineretrofitornek5.databinding.RowLayoutBinding

class RecyclerViewAdapter
    (private val cryptoList : ArrayList<CryptoModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors : Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    inner class RowHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind metodu verileri ilgili componentlere bağlamak için kullandığımız fonksiyon
        fun bind(cryptoModel: CryptoModel, position: Int) {
            binding.root.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }

            binding.root.setBackgroundColor(Color.parseColor(colors[position % colors.size]))
            binding.textName.text = cryptoModel.currency
            binding.textPrice.text = cryptoModel.price
        }
    }

    //Kendisine gelecek hir bir eleman için önce view in çizilmesinden sorumlu olacak
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    //ilgili listedeki objenin propertleri
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], position)
    }

    override fun getItemCount(): Int = cryptoList.size
}