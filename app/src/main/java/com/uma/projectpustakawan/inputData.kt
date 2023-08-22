package com.uma.projectpustakawan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.uma.projectpustakawan.databinding.ActivityInputDataBinding
import com.uma.projectpustakawan.dbPustakawan.Pustakawan
import com.uma.projectpustakawan.dbPustakawan.pustakawanDB
import com.uma.projectpustakawan.dbPustakawan.pustakawanDao
import java.text.SimpleDateFormat
import java.util.*

class inputData : AppCompatActivity() {

    private lateinit var binding : ActivityInputDataBinding
    private lateinit var database : pustakawanDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)

        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = pustakawanDB.getInstances(applicationContext)
        binding.btnSimpan.setOnClickListener{

            if(binding.txtId.text.isNotEmpty() &&
                    binding.txtNama.text.isNotEmpty() &&
                    binding.txtStatus.text.isNotEmpty() &&
                    binding.txtTanggal.text.isNotEmpty()) {

                database.pustakawanDAO().insertData(
                    Pustakawan(
                        binding.txtId.text.toString().toInt(),
                        binding.txtNama.text.toString(),
                        binding.txtStatus.text.toString(),
                        binding.txtTanggal.text.toString()
                    )
                )

                binding.txtId.setText("")
                binding.txtNama.setText("")
                binding.txtStatus.setText("")
                binding.txtTanggal.setText("")

                startActivity(
                    Intent(this, MainActivity::class.java)
                )


            }else{
                Toast.makeText(applicationContext,"Silahkan isi semua data terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }

        this.setTangalPustakawan()

    }

    private fun setTangalPustakawan() {
        this.setTanggal()
        binding.txtTanggal.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { picker, tahun, bulan, tanggal ->
                    binding.txtTanggal.setText("" + tanggal + "-" + bulan + "-" + tahun)
                }, year, month, day
            )
            datePickerDialog.show()
        }
    }

    private fun setTanggal() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.txtTanggal.setText(dateTime)
    }
}
