package com.example.slidingnumbers

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.slidingnumbers.databinding.NumberViewBinding
import com.example.slidingnumbers.models.Brick


class TableAdapter(
    private var bricks: MutableList<Int>,
    private val clickListener: BrickClickListener
) : RecyclerView.Adapter<TableAdapter.NumberViewHolder>() {
    fun setItem(list: MutableList<Int>) {
        bricks.clear()
        bricks.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = NumberViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = bricks.size

    inner class NumberViewHolder(private val binding: NumberViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model = 0
        fun bind() {
            if (bricks[adapterPosition] == 9) {
                binding.root.isVisible = false
            } else {
                model = bricks[adapterPosition]
                binding.tvNumber.text = model.toString()
                binding.root.setOnClickListener {
                    //TODO
                    clickListener.onBrickClick(Brick(model))
                }
            }
        }
    }
}