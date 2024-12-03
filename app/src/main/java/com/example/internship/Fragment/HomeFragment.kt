package com.example.internship.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.internship.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find CardViews for navigation
        val calendarCard: CardView = view.findViewById(R.id.card_activity_calendar)
        val progressCard: CardView = view.findViewById(R.id.card_progress)
        val tipsCard: CardView = view.findViewById(R.id.card_tips)

        // Set click listeners for navigation
        calendarCard.setOnClickListener {
            findNavController().navigate(R.id.calenderFragment)
        }

        progressCard.setOnClickListener {
            findNavController().navigate(R.id.progressFragment)
        }

        tipsCard.setOnClickListener {
            findNavController().navigate(R.id.tipsFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Optional: Additional setup or view initialization can be done here
        setupWelcomeImage(view)
    }

    private fun setupWelcomeImage(view: View) {
        val welcomeImage: ImageView = view.findViewById(R.id.welcome_image)
        // You can set a default welcome image or load it dynamically
        welcomeImage.setImageResource(R.drawable.welcome_illustration)
    }
}
