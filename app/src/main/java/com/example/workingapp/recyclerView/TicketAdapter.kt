package com.example.workingapp.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workingapp.R

class TicketAdapter(
    private val ticketList: List<Ticket>,
    private val ticketClickListener: OnTicketClickListener
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    //Se crea una interfaz con la función al hacer click
    interface OnTicketClickListener {
        fun onItemClick()
    }

    //Que va a cargar cuando se cree una view nueva (nuevo item en el recycler)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_ticket, parent, false)
        return TicketViewHolder(view)
    }

    //Va a linkear el contenido de las vistas a las mismas.
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        //Metodo para accionar cada detalle, nombre, descripcion, etc.
        holder.bind(ticket)
    }

    //Cantidad total de elementos, va a ser igual a la cantidad de la lista.
    override fun getItemCount(): Int = ticketList.size

    inner class TicketViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        //Asigno dentro del ViewHolder a que hace referencia cada variable.
        private val tittle = view.findViewById<TextView>(R.id.textTituloTicket)
        private val description = view.findViewById<TextView>(R.id.textDescripcionTicket)
        private val autor = view.findViewById<TextView>(R.id.textNombreAutor)
        private val date = view.findViewById<TextView>(R.id.textDateTicket)

        fun bind(ticket: Ticket) {
            tittle.text = ticket.titulo
            description.text = ticket.descripcion
            autor.text = ticket.autor
            date.text = ticket.fecha.toString()
            //Ejecuto el setOnClickListener en el view, utilizando la variable de la interface creada y su metodo.
            view.setOnClickListener { ticketClickListener.onItemClick() }
        }


    }


}