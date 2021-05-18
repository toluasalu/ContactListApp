package com.example.contactlist.data

import com.example.contactlist.model.Category
import com.example.contactlist.model.Contact

class Datasource {
    fun loadContacts(): List<Contact> {
        return listOf<Contact>(
            Contact("Dad", "08033939264"),
            Contact("Mum", "08138290702"),
            Contact("Sister", "08106328263")
        )
    }

    fun loadCategories(): List<Category> {
        return listOf<Category>(
            Category("Family"),
            Category("Friends"),
            Category("Mentors")
        )
    }
}