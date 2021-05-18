package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.adapter.CategoryAdapter
import com.example.contactlist.adapter.ContactAdapter
import com.example.contactlist.databinding.ActivityMainBinding
import com.example.contactlist.model.Contact
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listOfContacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val categoryName = intent?.extras?.getString(CategoryAdapter.CATEGORY_EXTRA)
        title = categoryName

        setContentView(view)

        val recyclerView = binding.recyclerView
        val adapter = ContactAdapter(this, listOfContacts)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Use the dialogBuilder to inflate our Alert dialog layout
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        dialogBuilder.setView(dialogView)

        //Get references to the edit_texts and Button in the Alert dialog layout
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.name_input)
        val phoneNumberInput = dialogView.findViewById<TextInputEditText>(R.id.number_input)
        val saveButton = dialogView.findViewById<Button>(R.id.saveBtn)

         phoneNumberInput?.addTextChangedListener( object:TextWatcher {
             override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

             override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                 if(p0?.length == 11) saveButton.isEnabled = true
             }

             override fun afterTextChanged(p0: Editable?) {}

         })

        //Create AlertDialog
        val alertDialog = dialogBuilder.create()
        //Show AlertDialog
        binding.fab.setOnClickListener {
            alertDialog.show()
        }


        saveButton?.setOnClickListener {
            val contact = Contact(nameInput.text.toString(), phoneNumberInput.text.toString())
            listOfContacts.add(contact)
            adapter.setupContacts(listOfContacts)
            clearInput(nameInput, phoneNumberInput)
            alertDialog.dismiss()
        }



    }

    private fun clearInput(
        nameInput: TextInputEditText,
        phoneNumberInput: TextInputEditText
    ) {
        nameInput.text?.clear()
        phoneNumberInput.text?.clear()
    }
}