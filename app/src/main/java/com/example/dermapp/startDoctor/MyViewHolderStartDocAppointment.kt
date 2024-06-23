package com.example.dermapp.startDoctor

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dermapp.R

class MyViewHolderStartDocAppointment(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val firstNamePat : TextView
    //val lastNamePat : TextView
    val appointmentDate : TextView
    val deleteButton: Button
    val seeDetailsButton: Button
    init {
        firstNamePat = itemView.findViewById(R.id.textViewPatientStartDocAppointments)
        //lastNamePat = itemView.findViewById(R.id.textViewSurnameStartDocAppointments)
        appointmentDate = itemView.findViewById(R.id.textViewDateStartDocAppointments)
        deleteButton = itemView.findViewById(R.id.buttonDeleteAppointmentDoc)
        seeDetailsButton = itemView.findViewById(R.id.seeDetailsButtonStartAppointmentDoc)
    }
}