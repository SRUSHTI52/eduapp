package com.example.internship.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internship.ActivityAdapter
import com.example.internship.ActivityItem
import com.example.internship.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var selectedDateActivity: TextView
    private lateinit var activityList: RecyclerView
    private val allActivities = mutableListOf<ActivityItem>()
    private val filteredActivities = mutableListOf<ActivityItem>()
    private lateinit var adapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)

        calendarView = view.findViewById(R.id.calendarView)
        selectedDateActivity = view.findViewById(R.id.selectedDateActivity)
        activityList = view.findViewById(R.id.activityList)

        activityList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ActivityAdapter(filteredActivities)
        activityList.adapter = adapter

        loadActivitiesFromJson()

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            updateActivitiesForDate(selectedDate)
        }

        return view
    }

    private fun loadActivitiesFromJson() {
        val inputStream = resources.openRawResource(R.raw.activities)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val activitiesArray: JSONArray = jsonObject.getJSONArray("activities")

        for (i in 0 until activitiesArray.length()) {
            val activityObj = activitiesArray.getJSONObject(i)
            val date = activityObj.getString("date")
            val activity = activityObj.getString("activity")
            val description = activityObj.getString("description")

            allActivities.add(ActivityItem(date, activity, description))
        }
    }

    private fun updateActivitiesForDate(date: String) {
        filteredActivities.clear()
        filteredActivities.addAll(allActivities.filter { it.date == date })
        adapter.notifyDataSetChanged()
    }
}
