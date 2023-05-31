package com.example.lab21.ui.questions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab21.databinding.ActivityAnswerBinding
import com.example.lab21.ui.SharedViewModel
import com.github.mikephil.charting.data.PieEntry
import org.tensorflow.lite.support.label.Category
import java.util.*

class AnswerActivity : AppCompatActivity(), TextClassificationHelper.TextResultsListener {
    private lateinit var binding: ActivityAnswerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("category" )
        val title = intent.getStringExtra("title")

        binding.categoryTextView.text = category
        binding.titleTextView.text = title
        binding.submitButton.setOnClickListener{
            classify(binding.answerEditText.text.toString())
        }
        }

        private fun classify(answer: String){
            val textClassificationHelper = TextClassificationHelper(context = this, currentModel = "mobilebert.tflite", listener = this)
            val classificationResult = textClassificationHelper.classify(answer)
        }

    override fun onError(error: String) {
        runOnUiThread {
            Toast.makeText(this@AnswerActivity, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }
    private fun getCategoryIndex(category: String): Float {
        return when (category.uppercase()) {
            "SLEEP" -> 0f
            "NUTRITION" -> 1f
            "STRESS" -> 2f
            "ALCOHOL" -> 3f
            else -> -1f
        }
    }



    override fun onResult(results: List<Category>, inferenceTime: Long) {
        val topResult = results.maxByOrNull { it.score }
        if (topResult != null) {
            val sharedPreferences = getSharedPreferences("results", Context.MODE_PRIVATE)
            val sharedPreferencesEditor = sharedPreferences.edit()
            val categoryIndex = getCategoryIndex(topResult.label)
            val entryString = "${categoryIndex}:${topResult.score}"

            val existingEntries = sharedPreferences.getStringSet("entries", mutableSetOf()) ?: mutableSetOf()
            existingEntries.add(entryString)
            sharedPreferencesEditor.putStringSet("entries", existingEntries).apply()
        }
        finish()
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEAD", "CONFIRMED")
    }

    override fun onPause() {
        super.onPause()
Log.d("PAUSE", "CONFIRMED")
    }
}
