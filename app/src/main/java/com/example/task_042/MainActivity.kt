package com.example.task_042

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.task_042.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarMain)
        title = "Открытый доступ."

        binding.cameraPermissionBTN.setOnClickListener{
            val permission = Manifest.permission.CAMERA
            permissonCameraLauncher.launch(permission)
        }

        binding.contactsPermissionBTN.setOnClickListener{
            val permission = Manifest.permission.READ_CONTACTS
            permissonContactsLauncher.launch(permission)
        }
    }

    private val permissonCameraLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
            isGranted ->
        if (isGranted) {
            permissionGranted()
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }

    private val permissonContactsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted ->
        if (isGranted) {
            permissionGranted()
            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun permissionGranted() {
        Toast.makeText(this@MainActivity, "Разрешение получено.", Toast.LENGTH_SHORT).show()
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