package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
         val dataList = mutableListOf<DataItem>()

        for(i in 1..24){
            var randomNo = Random.nextInt(2)
            var check = Random.nextBoolean()
            if(check) {
                dataList.add(DataItem(0,"title $i ",null))
            } else  {
                dataList.add(DataItem(1,null,"Sub-tittle $i"))
            }
        }
        val adapter = MyAdaptor(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
    }
}
