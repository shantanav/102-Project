package com.example.ritschedulingapptry2.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ritschedulingapptry2.Event
import com.example.ritschedulingapptry2.MainActivity
import com.example.ritschedulingapptry2.R
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        val mainActivity: MainActivity = this.activity as MainActivity
        val adapter = this.context?.let { it ->
            ArrayAdapter<Event>(
                it,
                R.layout.listview_item,
                mainActivity.events.toMutableList()
                    .filter { it.getTime().dayOfYear == LocalDateTime.now().dayOfYear }
                    .sortedBy { it.getTime() }
            )
        }
        val listView: ListView = mainActivity.findViewById(R.id.events_list)
        listView.adapter = adapter
    }
}