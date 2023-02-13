package com.example.pdfreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfreader.Adapter.PdfAdapter
import com.example.pdfreader.Model.PdfModel
import com.example.pdfreader.ViewModel.MainViewModel
import com.example.pdfreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewmodel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPdf()
    }

    private fun getPdf(){

        viewmodel.checkPermission1(this@MainActivity, this)

        viewmodel.pdfList.observe(this, Observer {
            initRecycler(it)
        })
    }

    private fun initRecycler(pdfList: ArrayList<PdfModel>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PdfAdapter(pdfList)
    }
}