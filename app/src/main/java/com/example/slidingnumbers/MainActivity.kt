package com.example.slidingnumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.slidingnumbers.databinding.ActivityMainBinding
import com.example.slidingnumbers.models.Brick
import java.text.FieldPosition
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var initialList: MutableList<Int>
    private lateinit var matrix: Array<IntArray>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TableAdapter

    companion object {
        const val col = 3
        const val row = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createTable()
    }

    private fun generateNumbers() {
        initialList = IntRange(0, col * row - 1).shuffled().toMutableList()
        matrix = Array(col) { IntArray(row) }
        var index = 0
        for (i in 0 until col) {
            for (j in 0 until row) {
                matrix[i][j] = initialList[index]
                index++
            }
        }
    }

    private fun createTable() {
        generateNumbers()

        adapter = TableAdapter(initialList, object : BrickClickListener {
            override fun onBrickClick(brick: Brick) {
                if (isMovable(brick.position, brick.positionEmpty)) {
                    swap(brick)
                    adapter.setList(initialList)
                    d("aCT SET LIT", initialList.joinToString(" , "))
                    adapter.notifyDataSetChanged()
                }
            }
        })
        binding.table.layoutManager = GridLayoutManager(this, col)
        binding.table.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    /** check if the clicked brick can move */
    private fun isMovable(position: Int, positionEmpty: Int): Boolean {

        var res = false

        val brickIndex = getIndexes(position)
        val emptyIndexes = getIndexes(positionEmpty)
        val movables = movablePositions(emptyIndexes)

        for (i in 0 until movables.size) {
            if (movables[i].contentEquals(brickIndex))
                res = true
        }
        d("ISMOVABLE", "$res")
        return res
    }

    /**switch the positions of the brick and empty space*/
    private fun swap(brick: Brick) {
        d("LIST BEFORE", initialList.toString())
        val temp = initialList[brick.position]
        initialList[brick.position] = initialList[brick.positionEmpty]
        initialList[brick.positionEmpty] = temp
        d("LIST AFTER", initialList.toString())
    }

    /** get two-dimensional indexes from the list position*/
    private fun getIndexes(p: Int): Array<Int> {
        val colIndex = p / col
        val rowIndex = p % col
        return arrayOf(colIndex, rowIndex)
    }

    /** get movable brick positions for the current empty position */
    private fun movablePositions(position: Array<Int>): MutableList<Array<Int>> {
        val colIndex = position[0]
        val rowIndex = position[1]

        val movables = mutableListOf<Array<Int>>()

        if (colIndex - 1 >= 0) {
            //UP
            movables.add(arrayOf(colIndex - 1, rowIndex))
        }
        if (colIndex + 1 <= col - 1) {
            //DOWN
            movables.add(arrayOf(colIndex + 1, rowIndex))
        }
        if (rowIndex - 1 >= 0) {
            //LEFT
            movables.add(arrayOf(colIndex, rowIndex - 1))
        }
        if (rowIndex + 1 <= row - 1) {
            //RIGHT
            movables.add(arrayOf(colIndex, rowIndex + 1))
        }

        for (i in 0 until movables.size) {
            d("MOV", movables[i].joinToString(" ,"))
        }
        return movables
    }


}