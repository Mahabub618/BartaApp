package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var edtForgot: TextView

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)
        edtForgot = findViewById(R.id.forgotPassword)


        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            if(email.isNullOrEmpty() || password.isNullOrEmpty())
            {
                Toast.makeText(this@Login, "Email Address or Password is not provided", Toast.LENGTH_SHORT).show()
            }
            else
            {
                login(email, password)
            }
        }

        edtForgot.setOnClickListener {
            val intent = Intent(this@Login, ResetPassword::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (mAuth.currentUser!!.isEmailVerified) {
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                    else
                    {
                        Toast.makeText(this@Login, "Email Address is not Verified. Please verify your email address", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    mAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener(this@Login){task ->
                            if(task.isSuccessful){
                                Toast.makeText(this@Login, "Entered Wrong Password", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this@Login, "User does not exist", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
    }
}