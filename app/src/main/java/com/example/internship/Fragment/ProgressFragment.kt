package com.example.internship.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.internship.R

class ProgressFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_progress, container, false)

        setupCircularProgress(
            view.findViewById(R.id.reading_progress),
            view.findViewById(R.id.reading_progress_text),
            "Reading",
            75
        )

        setupCircularProgress(
            view.findViewById(R.id.learning_progress),
            view.findViewById(R.id.learning_progress_text),
            "Learning",
            90
        )

        setupCircularProgress(
            view.findViewById(R.id.activity_progress),
            view.findViewById(R.id.activity_progress_text),
            "Physical Activity",
            65
        )

        return view
    }

    private fun setupCircularProgress(
        progressBar: ProgressBar,
        textView: TextView,
        title: String,
        progressPercentage: Int
    ) {
        progressBar.progress = progressPercentage
        textView.text = "$title: $progressPercentage%"
    }
}