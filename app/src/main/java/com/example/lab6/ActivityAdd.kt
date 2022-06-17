package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ryudith.lab6.room.EntityUser
import com.ryudith.lab6.room.ViewModelUser
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ActivityAdd : AppCompatActivity() {
    private lateinit var viewModel : ViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        val inputName = findViewById<EditText>(R.id.et_activityAdd_name)
        val inputEmail = findViewById<EditText>(R.id.et_activityAdd_email)

        findViewById<Button>(R.id.btn_activityAdd_save).setOnClickListener {
            val user = EntityUser(id=0, titulo =inputName.text.toString(), descripcion =inputEmail.text.toString(), valoracion = 5, tipoTurismo = "Tipo")
            viewModel.add(user)

            inputName.setText("")
            inputEmail.setText("")
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        }
    }
}