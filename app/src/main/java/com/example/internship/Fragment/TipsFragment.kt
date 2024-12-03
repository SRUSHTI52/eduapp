package com.example.internship.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internship.R
import com.example.internship.TipItem
import com.example.internship.TipsAdapter
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class TipsFragment : Fragment() {

    private lateinit var tipsRecyclerView: RecyclerView
    private val tipsList = mutableListOf<TipItem>()
    private lateinit var adapter: TipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tips, container, false)

        tipsRecyclerView = view.findViewById(R.id.tipsRecyclerView)
        tipsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TipsAdapter(tipsList)
        tipsRecyclerView.adapter = adapter

        loadTipsFromJson()

        return view
    }

    private fun loadTipsFromJson() {
        val inputStream = resources.openRawResource(R.raw.parenting_tips)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val tipsArray: JSONArray = jsonObject.getJSONArray("tips")

        for (i in 0 until tipsArray.length()) {
            val tipObj = tipsArray.getJSONObject(i)
            val title = tipObj.getString("title")
            val content = tipObj.getString("content")

            tipsList.add(TipItem(title, content))
        }

        adapter.notifyDataSetChanged()
    }
}
