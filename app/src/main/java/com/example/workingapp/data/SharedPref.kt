package com.example.workingapp.data

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context){
    internal var mySharedPref: SharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE)

    fun setNightModeState (state: Boolean?){
        val editor: SharedPreferences.Editor = mySharedPref.edit()
        editor.putBoolean("Night Mode", state!!)
        editor.apply()
    }

    fun loadNightModeState():Boolean?{
        return mySharedPref.getBoolean("Night Mode", false)
    }

}
