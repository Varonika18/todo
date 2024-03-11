package com.android.todo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private val todoAdapter = TodoAdapter(
        onItemChecked = { /* Handle checkbox click */ },
        onDeleteClicked = {
                deletedItem -> onDeleteClicked(deletedItem)

        }
    )
    lateinit var addfab : ExtendedFloatingActionButton
    lateinit var recyclerView :RecyclerView
    var  current_time: Long = System.currentTimeMillis()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addfab=findViewById(R.id.add_task);
        recyclerView=findViewById(R.id.task_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = todoAdapter


//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.SECOND, 10) // Set the time 10 seconds from now
//        val targetTime = calendar.timeInMillis
//
//        val newItem = TodoItem("Task with fixed time", targetTime)
//        todoAdapter.addItem(newItem)
//
//        // Start the timer to check for expired items
        //startTimer()
        addfab.setOnClickListener {
          showAddTaskDialog()
        }


    }

//    private fun startTimer() {
//        val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                checkAndRemoveExpiredItems()
//            }
//
//            override fun onFinish() {
//                // Handle timer finish if needed
//            }
//        }
//
//        timer.start()
//    }

//    private fun checkAndRemoveExpiredItems() {
//        val currentTime = System.currentTimeMillis()
//
//        val itemsToRemove = todoAdapter.getExpiredItems(currentTime)
//
//        for (item in itemsToRemove) {
//            todoAdapter.removeItem(item)
//        }
//    }

    private fun showAddTaskDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
        val editTextTask: EditText = dialogView.findViewById(R.id.editTextTask)
        val date: TextView = dialogView.findViewById(R.id.date)
        val time: TextView = dialogView.findViewById(R.id.time)
        val buttonAdd: Button = dialogView.findViewById(R.id.buttonAdd)

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Add Task")
            .setView(dialogView)
            .setCancelable(true)
            .show()
        time.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Create a time picker dialog
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    // Update the EditText with the selected time
                    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    time.setText(formattedTime)
                },
                hour,
                minute,
                true // 24-hour format
            )

            // Show the time picker dialog
            timePickerDialog.show()
        }

        // Set up date picker
        date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a date picker dialog
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    // Update the EditText with the selected date
                    val formattedDate = "$selectedDayOfMonth-${selectedMonth + 1}-$selectedYear"
                    date.setText(formattedDate)
                },
                year,
                month,
                day
            )

            // Show the date picker dialog
            datePickerDialog.show()
        }

        // Compare with current time
      val currentDateTimeMillis = System.currentTimeMillis()




    

    buttonAdd.setOnClickListener {
            val newTask = editTextTask.text.toString().trim()
            val newTime = time.text.toString().trim()
            val newDate = date.text.toString().trim()

            if (newTask.isNotEmpty() && newTime.isNotEmpty() && newDate.isNotEmpty()) {
                val newItem = TodoItem(System.currentTimeMillis(), newTask,newTime,newDate)
                todoAdapter.submitList(todoAdapter.currentList + listOf(newItem))
                dialog.dismiss()
            }
//        Log.d("curr", currentDateTimeMillis.toString())
//        Log.d("select",selectedDateTimeMillis.toString())
        }
    }



    private fun onDeleteClicked(deletedItem: TodoItem) {

        val updatedList = todoAdapter.currentList.toMutableList()
        updatedList.remove(deletedItem)
        todoAdapter.submitList(updatedList)
    }


}




