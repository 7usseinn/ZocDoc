package com.example.zocdoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Button
import android.widget.Toast

class DoctorAdapter( val doctors: List<DoctorDataItem>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.nameTextView.text = doctors[position].name
        holder.locationTextView.text = doctors[position].location
        holder.fieldTextView.text = doctors[position].field

    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
         val locationTextView: TextView = itemView.findViewById(R.id.textViewLocation)
         val fieldTextView: TextView = itemView.findViewById(R.id.textViewField)
         val datePicker: DatePicker = itemView.findViewById(R.id.datePicker)
         val timePicker: TimePicker = itemView.findViewById(R.id.timePicker)
         val bookAppointmentButton: Button = itemView.findViewById(R.id.bookAppointmentButton)

        fun bind(doctor: DoctorDataItem) {
            nameTextView.text = doctor.name
            locationTextView.text = doctor.location
            fieldTextView.text = doctor.field
            bookAppointmentButton.setOnClickListener {
                // Handle booking appointment action here
                val selectedDate = "${datePicker.dayOfMonth}/${datePicker.month + 1}/${datePicker.year}"
                val selectedTime = "${timePicker.hour}:${timePicker.minute}"

                Toast.makeText(itemView.context, "Appointment booked successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
