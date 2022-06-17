package com.example.lab6
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ryudith.lab6.room.EntityUser
import com.ryudith.lab6.room.ViewModelUser
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ActivityEdit : AppCompatActivity() {
    private lateinit var viewModel : ViewModelUser
    private  var userId : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        userId = intent.extras?.get("DATA_ID") as Long
        val userData = viewModel.read(userId)

        val inputName = findViewById<EditText>(R.id.et_activityEdit_name)
        val inputEmail = findViewById<EditText>(R.id.et_activityEdit_email)

        userData.observe(this, Observer {
            if (it != null) {
                inputName.setText(it.titulo)
                inputEmail.setText(it.descripcion)

            }
        })

        findViewById<Button>(R.id.btn_activityEdit_save).setOnClickListener {
            val user = EntityUser(id= userId, titulo=inputName.text.toString(), descripcion = inputEmail.text.toString(), valoracion = 5, tipoTurismo = "tip")
            viewModel.edit(user)

            inputName.setText("")
            inputEmail.setText("")
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
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
            viewModel.deleteUser(userId)

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)

            Toast.makeText(this, "Data deleted", Toast.LENGTH_LONG).show()
        }
        dialog.setNegativeButton("No") {_, _ ->}
        dialog.setTitle("Delete User")
        dialog.setMessage("Are you sure ?")
        dialog.create().show()
    }
}