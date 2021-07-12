package com.example.workingapp.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workingapp.databinding.RowPronosticoBinding
import com.example.workingapp.model.Pronostico
import com.example.workingapp.model.WeatherModel
import com.squareup.picasso.Picasso
import retrofit2.Response

class PronosticoAdapter() :RecyclerView.Adapter<PronosticoAdapter.ViewHolder>(){

     var pronosticoList = mutableListOf<Pronostico>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowPronosticoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pronosticoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articulo = pronosticoList[position]
        holder.bind(articulo)
    }

       inner class ViewHolder(val binding: RowPronosticoBinding) : RecyclerView.ViewHolder(binding.root){

           fun bind(pronostico: Pronostico) {

               binding.txtDia.text = pronostico.dia
               binding.txtFecha.text = pronostico.fecha
               binding.txtEstadoPronostico.text = pronostico.estado
               binding.txtPronosticoViento.text = pronostico.viento
               binding.txtTempMax.text = pronostico.tMax
               binding.txtTempMin.text = pronostico.tMin

               Picasso.get().load(pronostico.imagenPronostico).into(binding.imgPronostico)
           }
       }
}
