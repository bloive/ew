package com.example.slidingnumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slidingnumbers.databinding.ActivityMainBinding
import com.example.slidingnumbers.models.Brick

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TableAdapter
    private val size = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createTable()
    }

    private fun createTable() {
        val shuffled = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled().toMutableList()
        val array = Array(3) { IntArray(3) }
        for (i in 0..2) {
            for (j in 0..2) {

            }
        }


//        get2D(shuffled, size)
        
        adapter = TableAdapter(shuffled, object : BrickClickListener {
            override fun onBrickClick(brick: Brick) {
                TODO("Not yet implemented")
            }
        })
        binding.table.layoutManager = GridLayoutManager(this, size)
        binding.table.adapter = adapter
        adapter.notifyDataSetChanged()
    }
//
//    private fun get2D(list: MutableList<Int>, size : Int) {
//        for (n in list) {
//
//    }
}