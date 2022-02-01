package com.example.autoscrollrecyclerview

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scrollingText = ArrayList<String>()

        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display
        } else {
            windowManager.defaultDisplay
        }
        var width = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMatrices = windowManager.currentWindowMetrics
            val insets =
                windowMatrices.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            width = windowMatrices.bounds.width() - insets.left - insets.right
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        } else {
            val size = Point()
            display!!.getSize(size)
            width = size.x
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()

        }
        Toast.makeText(this, "width" + width, Toast.LENGTH_SHORT).show()

        val paint = text.paint
        val displayData = resources.getString(R.string.text_training)
        val words = displayData.split(" ")
        var line = ""
        for (word in words) {
            if (paint.measureText(line + " " + word, 0, line.length + word.length) < width - 100) {
                line = line + " " + word
            } else {
                scrollingText.add(line)
                line = "" + word
            }
        }

        setupRecyclerView(scrollingText)
        Handler().postDelayed({
            initAutoScrolling(scrollingText)
        }, 5000)
        initAutoScrolling(scrollingText)

    }

    private fun setupRecyclerView(scrollingText: ArrayList<String>) {

        val manager = ScreollLayout(applicationContext)
        rv_display_scrolling_message.layoutManager = ScreollLayout(this)
        val adapter = RvAdapter(scrollingText)
        rv_display_scrolling_message.adapter = adapter

        rv_display_scrolling_message.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                initAutoScrolling(scrollingText)
            }
        })
    }

    fun initAutoScrolling(scrollingText: ArrayList<String>) {
        var i = 0
        while (i < scrollingText.size) {
            rv_display_scrolling_message.smoothScrollToPosition(i)
            i++
        }
    }
}