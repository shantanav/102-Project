package com.example.ritschedulingapptry2.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ritschedulingapptry2.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(
            R.layout.fragment_dashboard,
            container, false
        )
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner,
            Observer { textView.text = it })

        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false);
        val addButton: Button = view.findViewById(R.id.add_button)
        addButton.setOnClickListener(fun(_: View) { createEvent(container, inflater) })

        return view
    }

    private fun createEvent(container: ViewGroup?, inflater: LayoutInflater) {
        val builder = container?.context?.let { AlertDialog.Builder(it) }
        val title = TextView(container?.context)
        title.text = getString(R.string.event_create_header);
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.gravity = Gravity.CENTER;
        title.setTextColor(Color.WHITE);
        title.textSize = 20F;
        builder?.setCustomTitle(title)

        builder?.setView(inflater.inflate(R.layout.dialog_create_event, null))
            ?.setPositiveButton(R.string.yes) { _, _ ->
                Toast.makeText(
                    container.context,
                    "Event created", Toast.LENGTH_SHORT
                ).show()
            }
            ?.setNegativeButton(R.string.cancel) { _, _ ->
                Toast.makeText(
                    container.context,
                    "Event not created", Toast.LENGTH_SHORT
                ).show()
        }

        builder?.show()
    }
}
