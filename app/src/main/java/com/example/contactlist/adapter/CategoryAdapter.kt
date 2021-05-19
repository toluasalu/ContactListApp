package com.example.contactlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.R
import com.example.contactlist.adapter.CategoryAdapter.*
import com.example.contactlist.model.Category
import android.content.Intent
import com.example.contactlist.MainActivity


class CategoryAdapter(private val context: Context, private val dataset: List<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val buttonView = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return CategoryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = dataset[position]
        holder.buttonView.text = category.name
        val btnText = holder.buttonView.text.toString()
        val currentContext = holder.view.context
        val contactListIntent = Intent(currentContext, MainActivity::class.java)
        contactListIntent.putExtra(CATEGORY_EXTRA, btnText)

        holder.buttonView.setOnClickListener {
            currentContext.startActivity(contactListIntent)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    companion object {
        const val CATEGORY_EXTRA = "CATEGORY"
    }
}