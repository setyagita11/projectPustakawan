package com.uma.projectpustakawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uma.projectpustakawan.dbPustakawan.Pustakawan

                                                          //step 3 membuat var listener yg extend ke interface
class adapterPustakawan ( val list: ArrayList<Pustakawan>, var listener : OnClickListener) : RecyclerView.Adapter<adapterPustakawan.ViewHolder> () {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val idPustaka = itemView.findViewById<TextView>(R.id.inputId)
        val namaPustaka = itemView.findViewById<TextView>(R.id.inputNama)
        val statusPustaka = itemView.findViewById<TextView>(R.id.inputStatus)
        val tanggalRegistrasi = itemView.findViewById<TextView>(R.id.inputTanggal)
        //step 1 membuat variabel untuk tombol icon delete dan edit
        val deleteData = itemView.findViewById<ImageView>(R.id.btnDelete)
        val updateData = itemView.findViewById<ImageView>(R.id.btnUpdate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_pustakawan, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pustakawan = list[position]
        holder.idPustaka.text = list[position].no_pustakawan.toString()
        holder.namaPustaka.text = list[position].nama_pustakawan
        holder.statusPustaka.text = list[position].status
        holder.tanggalRegistrasi.text = list[position].tanggal_registrasi
        //step 4 membuat holder supaya bisa berfungsi
        holder.deleteData.setOnClickListener {
            listener.onDelete(list[position])
        }
        holder.updateData.setOnClickListener {
            listener.onEdit(list[position])
        }
    }

    fun setData(newList: List<Pustakawan>){
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //step 2 membuat interface untuk fun delete dan edit
    interface OnClickListener{
        fun onDelete(pustakawan: Pustakawan) // mengarahnya pada fun delete dan edit pada dao
        fun onEdit(pustakawan: Pustakawan)
    }

}