package com.example.ritschedulingapptry2.ui.dashboard

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ritschedulingapptry2.R
import com.example.ritschedulingapptry2.Event
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @RequiresApi(Build.VERSION_CODES.O)
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
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })

        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false);
        val addButton: Button = view.findViewById(R.id.add_button)
        addButton.setOnClickListener(@RequiresApi(Build.VERSION_CODES.O)
        fun(_) { createEvent(container, inflater, view) })

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createEvent(container: ViewGroup?, inflater: LayoutInflater, view: View) {
        val builder = container?.context?.let { AlertDialog.Builder(it) }
        val dialogView: View = inflater.inflate(R.layout.dialog_create_event, null)
        val nameView: EditText = dialogView.findViewById(R.id.event_name)
        val descriptionView: EditText = dialogView.findViewById(R.id.description)
        val datePicker: DatePicker = dialogView.findViewById(R.id.datePicker2)
        val timePicker: TimePicker = dialogView.findViewById(R.id.timePicker)
        val title = TextView(container?.context)
        title.text = getString(R.string.event_create_header);
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.gravity = Gravity.CENTER;
        title.setTextColor(Color.WHITE);
        title.textSize = 20F;
        builder?.setCustomTitle(title)
        builder
            ?.setView(dialogView)
            ?.setPositiveButton(R.string.yes) { _, _ ->
                val day = datePicker.dayOfMonth
                val month = datePicker.month
                val year = datePicker.year
                val hour = timePicker.hour
                val minute = timePicker.minute
                val setTime: LocalDateTime = LocalDateTime.of(year, month, day, hour, minute)
                val event: Event = Event(nameView.text.toString(),
                    descriptionView.text.toString(), setTime)
                Toast.makeText(container.context, event.toString(), Toast.LENGTH_SHORT).show()
            }?.setNegativeButton(R.string.cancel) { _, _ ->
                Toast.makeText(container.context, "Not created", Toast.LENGTH_SHORT).show()
            }
        builder?.show()
    }
}
