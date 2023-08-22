package com.uma.projectpustakawan

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.uma.projectpustakawan.databinding.ActivityMainBinding
import com.uma.projectpustakawan.dbPustakawan.Pustakawan
import com.uma.projectpustakawan.dbPustakawan.pustakawanDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val db by lazy { pustakawanDB.getInstances(this) } // memekai getInstences
    // private val db by lazy { pustakawanDB() } memakai invoke

    private lateinit var adapter : adapterPustakawan
    private lateinit var database : pustakawanDB

    private lateinit var binding : ActivityMainBinding

    private var dataID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //step 5
        adapter = adapterPustakawan(arrayListOf(),
            object : adapterPustakawan.OnClickListener{
                override fun onDelete(pustakawan: Pustakawan) {
                    //step 6 memanggil fun perintah untuk hapus data
                    deleteData(pustakawan)
                }

                override fun onEdit(pustakawan: Pustakawan) {
                    TODO("Not yet implemented")
                }

            }
        )

        binding.listData.adapter = adapter
        binding.listData.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this,inputData::class.java))
        }
    }

    //step 7 membuat perintah untuk hapus database
    private fun deleteData(pustakawan: Pustakawan) {

        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")

            setMessage("Apakah anda yakin akan menghapus data? ${pustakawan.nama_pustakawan}?")

            setNegativeButton("Cancel"){ //tombol cancel
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){
                dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.pustakawanDAO().hapusData(pustakawan)
                    finish()
                    startActivity(intent)
                }
                tampilSemuaData()
            }
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        tampilSemuaData()
    }

    fun tampilSemuaData(){
        binding.listData.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.pustakawanDAO().getAllData()
            adapter.setData(data)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
        binding.listData.adapter = adapter
    }
}