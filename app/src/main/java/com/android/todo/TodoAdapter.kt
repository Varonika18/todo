package com.android.todo

import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val onItemChecked: (TodoItem) -> Unit,
                  private val onDeleteClicked: (TodoItem) -> Unit) :
    ListAdapter<TodoItem, TodoViewHolder>(TodoDiffCallback()) {


    private val handler = Handler()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_task, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)

        holder.checkBox.isChecked = item.isCompleted
        holder.taskText.text = item.task
        holder.taskTime.text= item.time
        holder.taskDate.text= item.date
//        val currentTimeMillis = System.currentTimeMillis()
//        val timeUntilExpiration = item.expirationTimeMillis - currentTimeMillis
//
//        if (timeUntilExpiration > 0) {
//            // Set up a CountDownTimer to update the UI and delete the item when it expires
//            holder.countDownTimer?.cancel() // Cancel existing timer if any
//            holder.countDownTimer = object : CountDownTimer(timeUntilExpiration, 1000) {
//                override fun onTick(millisUntilFinished: Long) {
//                    // Update the UI with the remaining time (you can format it as needed)
//                    holder.taskText.text = "${item.task} - ${millisUntilFinished / 1000} seconds left"
//                }
//
//                override fun onFinish() {
//                    // Delete the item when the timer finishes
//                    onDeleteClicked(item)
//                }
//            }.start()
//        } else {
//            // The expiration time has already passed, delete the item immediately
//            onDeleteClicked(item)
//        }

        holder.checkBox.setOnClickListener {
            onItemChecked(item)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClicked(item)
        }
      //  scheduleDeletion(item, holder.itemView)
    }
//    private fun scheduleDeletion(item: TodoItem, itemView: View) {
//        val currentTime = System.currentTimeMillis()
//        val delayMillis = item.times - currentTime
//
//        if (delayMillis > 0) {
//            handler.postDelayed({
//                val position = currentList.indexOf(item)
//                if (position != RecyclerView.NO_POSITION) {
//                    currentList.toMutableList().remove(item)
//                    notifyItemRemoved(position)
//                }
//            }, delayMillis)
//        }
//    }
//    fun addItem(newItem: TodoItem) {
//
//    }
//
//    fun getExpiredItems(currentTime: Long): Any {
//
//    }
//
//    fun removeItem(item: Any) {
//
//    }


}