package com.example.playsnapui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var buttonBack : AppCompatButton
    lateinit var titleRegister : TextView
    lateinit var subtitleRegister : TextView
    lateinit var logoOrang : ImageView
    lateinit var textNama : TextInputEditText
    lateinit var logoEmail : ImageView
    lateinit var textEmail : TextInputEditText
    lateinit var logoUser : ImageView
    lateinit var textUser : TextInputEditText
    lateinit var logoPassword : ImageView
    lateinit var textPassword : TextInputEditText
    lateinit var logoConfirm : ImageView
    lateinit var textConfirm : TextInputEditText
    lateinit var buttonDaftar : AppCompatButton
    lateinit var textRegisterNonClickable : TextView
    lateinit var textRegisterClickable : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonBack = findViewById(R.id.btn_back)
        titleRegister = findViewById(R.id.tv_register_title)
        subtitleRegister = findViewById(R.id.tv_register_subtitle)
        logoOrang = findViewById(R.id.ic_account_rectangle)
        textNama = findViewById(R.id.et_fullname)
        logoEmail = findViewById(R.id.ic_lock)
        textEmail = findViewById(R.id.et_email)
        logoUser = findViewById(R.id.ic_account)
        textUser = findViewById(R.id.et_username)
        logoPassword = findViewById(R.id.ic_password)
        textPassword = findViewById(R.id.et_password)
        logoConfirm = findViewById(R.id.ic_confirmpass)
        textConfirm = findViewById(R.id.et_confirmpass)
        buttonDaftar = findViewById(R.id.registerButton)
        textRegisterNonClickable = findViewById(R.id.register_have_account)
        textRegisterClickable = findViewById(R.id.masuk)

        textRegisterClickable.setOnClickListener{
            val explicit = Intent(this, LoginActivity::class.java)

            startActivity(explicit)
            finish()
        }

        buttonBack.setOnClickListener {
            val explicit1 = Intent(this, MainActivity::class.java)

            startActivity(explicit1)
            finish()
        }

        buttonDaftar.setOnClickListener{
            val nama = textNama.text.toString()
            val email = textEmail.text.toString()
            val username = textUser.text.toString()
            val password = textPassword.text.toString()
            val confirmPass = textConfirm.text.toString()
            if(nama.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this, "Please fill the empty form", Toast.LENGTH_LONG).show()
            }
            else if(password != confirmPass){
                Toast.makeText(this, "You confirm password is not match with the password", Toast.LENGTH_LONG).show()
            }
            else if(email.isNotEmpty() && password.isNotEmpty()){
                MainActivity.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val explicit2 = Intent(this, LoginActivity::class.java)
                        startActivity(explicit2)
                        finish()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}