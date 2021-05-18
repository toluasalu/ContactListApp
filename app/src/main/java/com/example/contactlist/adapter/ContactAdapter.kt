package com.example.contactlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.databinding.ListItemBinding
import com.example.contactlist.model.Contact


class ContactAdapter(private val context: Context, private val dataset: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contacts = mutableListOf<Contact>()


    class ContactViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(contact: Contact) {
            binding.name.text = contact.name
            binding.number.text = contact.phoneNumber

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val adapterLayout =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = dataset[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun setupContacts(listOfContacts: List<Contact>) {
        this.contacts.addAll(listOfContacts)
    }

}
