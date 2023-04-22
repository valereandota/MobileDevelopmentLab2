package com.example.lab21.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab21.R

class QuestionAdapter(private val onItemClick: (QuestionItem) -> Unit) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var questions: List<QuestionItem> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        private val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)

        fun bind(questionItem: QuestionItem) {
            categoryTextView.text = questionItem.category
            questionTextView.text = questionItem.title

            itemView.setOnClickListener {
                onItemClick(questionItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size

    fun updateQuestions(newQuestions: List<QuestionItem>) {
        questions = newQuestions
        notifyDataSetChanged()
    }
}