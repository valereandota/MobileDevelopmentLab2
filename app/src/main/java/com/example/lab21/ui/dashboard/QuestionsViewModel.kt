package com.example.lab21.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {
    private val _questionItems = MutableLiveData<List<QuestionItem>>().apply {
        value = listOf(
            QuestionItem("Sleep", "How did you sleep last night?"),
            QuestionItem("Nutrition", "How was your nutrition today?"),
            QuestionItem("Stress", "How stressed do you feel today?"),
            QuestionItem("Alcohol", "Did you consume alcohol today?")
        )
    }
    val questionItems: LiveData<List<QuestionItem>> = _questionItems
}