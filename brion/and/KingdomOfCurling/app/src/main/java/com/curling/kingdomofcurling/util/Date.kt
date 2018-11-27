package com.curling.kingdomofcurling.util

import java.util.*


fun getCurrentDate ( ):String{
    val c = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)

    return "${mYear}년 ${mMonth+1}월 ${mDay}일"
}