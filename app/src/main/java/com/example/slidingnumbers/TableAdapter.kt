package com.example.slidingnumbers

import android.graphics.Color
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slidingnumbers.databinding.NumberViewBinding
import com.example.slidingnumbers.models.Brick


class TableAdapter(
    private var list: MutableList<Int>,
    private val clickListener: BrickClickListener
) : RecyclerView.Adapter<TableAdapter.NumberViewHolder>() {

    private var positionEmpty = 0
    private var positionBrick = 0

    fun setList(list: MutableList<Int>){
        this.list = list
        d("ADAPTER SETITEM", list.joinToString(" ,"))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = NumberViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class NumberViewHolder(private val binding: NumberViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var value = 0
        fun bind() {
            if (list[adapterPosition] == 0) {
                positionEmpty = adapterPosition
                binding.tvNumber.text = ""
            } else {
                value = list[adapterPosition]
                binding.tvNumber.text = value.toString()
                binding.root.setOnClickListener {
                    positionBrick = adapterPosition

                    clickListener.onBrickClick(Brick(value, positionBrick, positionEmpty))
                }

                /** check if the brick is positioned correctly and change color*/
                if(value == adapterPosition + 1) {
                    binding.root.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }
}