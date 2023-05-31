package com.example.lab21.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieEntry

class SharedViewModel : ViewModel() {
    val pieEntryList = MutableLiveData<ArrayList<PieEntry>>().apply {
        value = ArrayList()
    }

    fun addPieEntry(pieEntry: PieEntry) {
        val currentList = pieEntryList.value ?: ArrayList()
        currentList.add(pieEntry)
        pieEntryList.value = currentList
        Log.d("LIST", pieEntryList.value.toString())
    }

    fun getCurrentList(): ArrayList<PieEntry> {
        return pieEntryList.value ?: ArrayList()
    }
}
