package com.example.qaydlar_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import com.example.qaydlar_01.Adapter.RvAdapter
import com.example.qaydlar_01.databinding.ActivityMainBinding
import com.example.qaydlar_01.db.MyDbHelper
import com.example.qaydlar_01.models.User

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var dbHelper: MyDbHelper
    lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper = MyDbHelper(this)


        binding.btnSave.setOnClickListener {
            binding.apply {
                val user  = User(edtName.text.toString(), edtNumber.text.toString())
                dbHelper.addUser(user)
                onResume()
                Toast.makeText(this@MainActivity,"Saqlandi", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onResume() {
        super.onResume()
        rvAdapter = RvAdapter(dbHelper.getUsers())
        binding.rv.adapter = rvAdapter
    }
}