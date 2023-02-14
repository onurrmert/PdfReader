package com.example.pdfreader.ViewModel

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.pdfreader.Model.PdfModel
import com.example.pdfreader.Util.CheckPermission

class MainViewModel : ViewModel(){

    val pdfList = MutableLiveData<ArrayList<PdfModel>>()

    fun checkPermission1(activity: Activity, lifecycleOwner: LifecycleOwner){

        CheckPermission.checkExternalPermission(activity.applicationContext)

        CheckPermission.pdfList.observe(lifecycleOwner, Observer {
            pdfList.value = it
        })
    }
}