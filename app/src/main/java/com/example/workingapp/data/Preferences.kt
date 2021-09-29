package com.example.workingapp.data

import android.content.SharedPreferences

class Preferences {
    companion object{
        fun getSharedPreferenceUserId(sharedPref: SharedPreferences):Int{
            return sharedPref.getInt("userId", 0)
        }
    }
}