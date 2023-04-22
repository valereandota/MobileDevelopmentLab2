package com.example.lab21.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab21.R
import com.example.lab21.databinding.ActivityAnswerBinding
import org.tensorflow.lite.support.label.Category

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
        Log.d("RESULT", classificationResult.toString())
        }

    override fun onError(error: String) {
        runOnUiThread {
            Toast.makeText(this@AnswerActivity, "Error: $error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResult(results: List<Category>, inferenceTime: Long) {
        runOnUiThread {
            // Process the results here. You can, for example, show the results in a TextView or store them in a variable
            val resultText = results.joinToString(separator = "\n") { "${it.label}: ${it.score}" }
            findViewById<TextView>(R.id.resultTextView).text = resultText

            // You can also check for specific labels or score thresholds to determine the classification
            val negativeLabel = "Negative"
            val accuracyThreshold = 0.8

            results.find { it.label == negativeLabel }?.let { negativeCategory ->
                if (negativeCategory.score >= accuracyThreshold) {
                    Log.d("RESULT", "NEGATIVE")

                    // The answer has a high negative classification score. Handle this case accordingly.
                }
            }
        }
    }
}
