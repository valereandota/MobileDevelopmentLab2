package com.example.lab21.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab21.databinding.FragmentQuestionBinding

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var questionsViewModel: QuestionsViewModel
    private lateinit var questionsAdapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        questionsViewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)

        // Initialize RecyclerView
        questionsAdapter = QuestionAdapter { questionItem ->
            val intent = Intent(context, AnswerActivity::class.java)
            intent.putExtra("category", questionItem.category)
            intent.putExtra("title", questionItem.title)
            startActivity(intent)
        }

        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = questionsAdapter
        }

        // Observe changes in question items
        questionsViewModel.questionItems.observe(viewLifecycleOwner) { questionItems ->
            questionsAdapter.updateQuestions(questionItems)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}