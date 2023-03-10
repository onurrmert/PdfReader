package com.example.pdfreader.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfreader.Model.PdfModel
import com.example.pdfreader.R
import com.example.pdfreader.databinding.PdfRowBinding
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity

class PdfAdapter (val pdfList : List<PdfModel>) : RecyclerView.Adapter<PdfAdapter.PdfViewHolder>(){

    class PdfViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val binding = PdfRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        return PdfViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pdf_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pdfList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {

        holder.binding.textPdf.setText(pdfList.get(position).pdfName)

        holder.binding.textModified.setText(pdfList.get(position).pdfLastModified)

        holder.binding.textPdf.setOnClickListener {
            showPdf(pdfList.get(position), holder.itemView.context)
        }

        holder.binding.recyclerRow.setOnClickListener {
            showPdf(pdfList.get(position), holder.itemView.context)
        }
    }

    private fun showPdf(pdfModel: PdfModel, context: Context){

        val uri = pdfModel.pdfUri

        val config = PdfActivityConfiguration.Builder(context).build()

        try {
            PdfActivity.showDocument(context, uri, null, config)
        }catch (e : Exception){
            println("error: " + e.localizedMessage)
        }
    }
}