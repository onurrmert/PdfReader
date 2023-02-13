package com.example.pdfreader.Util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pdfreader.Model.PdfModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class CheckPermission {

    companion object{

        val pdfList = MutableLiveData<ArrayList<PdfModel>>()

        fun checkExternalPermission(context: Context) {

            Dexter.withContext(context)
                .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener{
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        pdfList.value = FindPdfFile.getPdf()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Toast.makeText(context, "izin vermek zorundasınız", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                }).check()
        }
    }
}