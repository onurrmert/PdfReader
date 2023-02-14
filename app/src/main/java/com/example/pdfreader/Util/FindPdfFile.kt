package com.example.pdfreader.Util

import android.net.Uri
import android.os.Environment
import com.example.pdfreader.Model.PdfModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FindPdfFile {

    companion object{

        fun getPdf() : ArrayList<PdfModel>{

            val liste = ArrayList<PdfModel>()

            findFile(Environment.getExternalStorageDirectory()).forEach {
                val lastModified = Date(it.lastModified())
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                val formattedDateString: String = formatter.format(lastModified)

                liste.add(PdfModel(it.name, Uri.fromFile(it), formattedDateString))
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