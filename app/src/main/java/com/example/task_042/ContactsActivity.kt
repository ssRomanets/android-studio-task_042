package com.example.task_042

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_042.databinding.ActivityContactsBinding
import kotlin.system.exitProcess

class ContactsActivity : AppCompatActivity() {

    val contacts = ContactDataBase.contacts
    private lateinit var binding: ActivityContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarContacts)
        title = "Контакты."

        binding.recyclerContactsViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(contacts)
        binding.recyclerContactsViewRV.adapter = adapter
        binding.recyclerContactsViewRV.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}