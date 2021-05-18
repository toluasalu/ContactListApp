package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactlist.adapter.CategoryAdapter
import com.example.contactlist.data.Datasource
import com.example.contactlist.databinding.ActivityContactCategoryBinding
import com.example.contactlist.databinding.ActivityMainBinding

class ContactCategory : AppCompatActivity() {
    private lateinit var binding: ActivityContactCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactCategoryBinding.inflate(layoutInflater)
         setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val dataset = Datasource().loadCategories()
        val categoryAdapter = CategoryAdapter(this, dataset)
        recyclerView.adapter = categoryAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 4)


    }
}
