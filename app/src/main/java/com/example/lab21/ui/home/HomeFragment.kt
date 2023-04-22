package com.example.lab21.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lab21.R
import com.example.lab21.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    var fragment : Fragment? =  YoutubeFragment1();

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        load()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as AppCompatActivity

        val button1: Button = binding.btn1
        val button2: Button = binding.btn2
        val button3: Button = binding.btn3
        val button4: Button = binding.btn4
        val button5: Button = binding.btn5
        button1.setOnClickListener{
            fragment = YoutubeFragment1()
            load()
        }
        button2.setOnClickListener{
            fragment = YoutubeFragment2()
            load()
        }
        button3.setOnClickListener{
            fragment = YoutubeFragment3()
            load()
        }
        button4.setOnClickListener{
            fragment = YoutubeFragment4()
            load()
        }
        button5.setOnClickListener{
            fragment = YoutubeFragment5()
            load(
            )
        }

    }

    fun load(){
        Log.d("TEST", fragment.toString() )
        fragment?.let {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.playerFrame, it)
                ?.commit() ?: null
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}