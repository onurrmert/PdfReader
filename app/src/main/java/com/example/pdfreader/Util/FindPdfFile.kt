package com.example.pdfreader.Util

import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.pdfreader.Model.PdfModel
import java.io.File

class FindPdfFile {

    companion object{

        val pdfList = MutableLiveData<ArrayList<PdfModel>>()

        fun getPdf() : ArrayList<PdfModel>{

            val liste = ArrayList<PdfModel>()

            findFile(Environment.getExternalStorageDirectory()).forEach {
                liste.add(PdfModel(it.name, Uri.fromFile(it)))
            }
            return liste
        }

        private fun findFile(file: File): ArrayList<File> {

            val pdfFileList = ArrayList<File>()

            val pdfArrays = file.listFiles()

            for (singlePdf in pdfArrays!!){

                if (singlePdf.isDirectory && !singlePdf.isHidden){
                    pdfFileList.addAll(findFile(singlePdf))
                }else{
                    if (singlePdf.name.endsWith(".pdf")){
                        pdfFileList.add(singlePdf)
                    }
                }
            }
            return pdfFileList
        }
    }
}