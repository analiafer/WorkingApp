package com.example.workingapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workingapp.R
import com.example.workingapp.Ticket
import com.example.workingapp.databinding.RowTicketBinding

class TicketAdapter(
    private val ticketClickListener: OnTicketClickListener
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    private val tickets = mutableListOf<Ticket>()

    //Se crea una interfaz con la función al hacer click
    interface OnTicketClickListener {
        fun onItemClick()
    }

    //Que va a cargar cuando se cree una view nueva (nuevo item en el recycler)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = RowTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }

    //Va a linkear el contenido de las vistas a las mismas.
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = tickets[position]
        //Metodo para accionar cada detalle, nombre, descripcion, etc.
        holder.bind(ticket)
    }

    //Cantidad total de elementos, va a ser igual a la cantidad de la lista.
    override fun getItemCount(): Int = tickets.size

    fun submitList(it: List<Ticket>) {
        tickets.clear()
        tickets.addAll(it)

        notifyDataSetChanged()
    }

    inner class TicketViewHolder(binding: RowTicketBinding) : RecyclerView.ViewHolder(binding.root) {

        //Asigno dentro del ViewHolder a que hace referencia cada variable.
        private val tittle = binding.textTituloTicket
        private val description = binding.textDescripcionTicket
        private val autor = binding.textNombreAutor
        private val date = binding.textDateTicket
        private val card = binding.backgroundTextTicket

        fun bind(ticket: Ticket) {
            tittle.text = ticket.titulo
            description.text = ticket.descripcion
            autor.text = ticket.autor


            //Ejecuto el setOnClickListener en el view, utilizando la variable de la interface creada y su metodo.
            card.setOnClickListener { ticketClickListener.onItemClick() }
        }


    }


}