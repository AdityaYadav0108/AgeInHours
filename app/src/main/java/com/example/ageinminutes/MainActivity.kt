package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var tvAgeInHours:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInHours=findViewById(R.id.tvAgeInHours)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {
            ageConverter()
        }

        }
    private fun ageConverter(){
        val myCalendar=Calendar.getInstance()
        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val dayOfMonth= myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,{view, selectedYear, selectedMonth, selectedDayOfMonth->
            Toast.makeText(this,"Selected Date Is Year: $selectedYear Month:${selectedMonth+1} Day:$selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text=selectedDate
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInHours = theDate.time / 3600000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {

                    val currentDateInHours = currentDate.time / 3600000
                    val differenceInHours = currentDateInHours - selectedDateInHours
                    tvAgeInHours?.text = differenceInHours.toString()
                }
            }
        } , year, month, dayOfMonth)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()


        }
    }







