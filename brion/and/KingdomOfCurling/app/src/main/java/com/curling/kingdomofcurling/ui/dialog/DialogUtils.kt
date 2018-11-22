package com.curling.kingdomofcurling.ui.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*


fun showDatePickerCurrent (context: Context, listener:DatePickerDialog.OnDateSetListener) {
    val c = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)


    val datePickerDialog = DatePickerDialog(context, listener, mYear, mMonth, mDay)
    datePickerDialog.show()
}


fun showTimePickerCurrent (context: Context, listener: TimePickerDialog.OnTimeSetListener) {
    // Get Current Time
    val c = Calendar.getInstance()
    val mHour = c.get(Calendar.HOUR_OF_DAY)
    val mMinute = c.get(Calendar.MINUTE)

    // Launch Time Picker Dialog
    val timePickerDialog = TimePickerDialog(context, listener, mHour, mMinute, false)
    timePickerDialog.show()
}