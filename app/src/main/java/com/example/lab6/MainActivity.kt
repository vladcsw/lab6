package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryudith.lab6.room.EntityUser
import com.ryudith.lab6.room.ViewModelUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)


        findViewById<FloatingActionButton>(R.id.fab_activityMain_add).setOnClickListener {
            val intent = Intent(this, ActivityAdd::class.java)
            startActivity(intent)
        }

        val adapterUser = AdapterUserPaging()
        val rv = findViewById<RecyclerView>(R.id.rv_activityMain_browse)
        rv.adapter = adapterUser

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.browse().collect {
                adapterUser.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_main_delete) {
            confirmDelete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDelete () {
        val dialog = AlertDialog.Builder(this)
        dialog.setPositiveButton("Yes") {_, _ ->
            viewModel.deleteAll()

            Toast.makeText(this, "Data deleted", Toast.LENGTH_LONG).show()
        }
        dialog.setNegativeButton("No") {_, _ ->}
        dialog.setTitle("Delete All")
        dialog.setMessage("Are you sure ?")
        dialog.create().show()
    }
}