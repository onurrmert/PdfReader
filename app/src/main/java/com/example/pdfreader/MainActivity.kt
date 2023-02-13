package com.example.pdfreader

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pdfreader.Model.PdfModel
import com.example.pdfreader.ViewModel.MainViewModel
import com.example.pdfreader.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPdf()
    }

    private fun getPdf(){

        viewmodel.checkPermission1(this@MainActivity, this)

        viewmodel.pdfList.observe(this, Observer {

        })
    }

    private fun initRecycler(pdfList: ArrayList<PdfModel>){

    }
}