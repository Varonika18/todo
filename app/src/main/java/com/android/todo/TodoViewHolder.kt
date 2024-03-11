package com.android.todo

import android.os.CountDownTimer
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        val checkBox: CheckBox = itemView.findViewById(R.id.task_completed)
        val taskText: TextView = itemView.findViewById(R.id.task_description)
        val taskTime: TextView= itemView.findViewById(R.id.task_time);
        val taskDate: TextView=itemView.findViewById(R.id.task_date)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_task)
      //  var countDownTimer: CountDownTimer? = null


}
