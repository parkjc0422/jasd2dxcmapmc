package com.curling.kingdomofcurling.util

import android.text.TextUtils
import android.util.Patterns

fun isEmailFormat(emailAddress:String) :Boolean=  !TextUtils.isEmpty(emailAddress) && Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()