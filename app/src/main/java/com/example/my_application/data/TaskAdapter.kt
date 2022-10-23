package com.example.my_application.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_application.R

class TaskAdapter(val taskList: Array<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskStartLocView: TextView = itemView.findViewById(R.id.text_departs_from)
        private val taskEndLocView: TextView = itemView.findViewById(R.id.text_arrives_to)
        private val taskStartTimeView: TextView = itemView.findViewById(R.id.time_departs_at)
        private val taskEndTimeView: TextView = itemView.findViewById(R.id.time_arrives_at)
        private val taskCapacity: TextView = itemView.findViewById(R.id.text_capacity)

        fun bind(task: Task) {
//            taskStartLocView.text = getString(R.string.place, task.GetStopPoint());
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return TaskViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return taskList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }
}