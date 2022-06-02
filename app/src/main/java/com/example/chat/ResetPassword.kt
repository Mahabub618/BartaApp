package com.example.chat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {

    private lateinit var edtgmail: EditText
    private lateinit var btnReset: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        edtgmail = findViewById(R.id.resetEmail)
        btnReset = findViewById(R.id.buttonReset)
        mAuth = FirebaseAuth.getInstance()

        btnReset.setOnClickListener {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            if(edtgmail.text.toString().isNullOrEmpty())
            {
                Toast.makeText(this@ResetPassword, "Email Address is not provided", Toast.LENGTH_SHORT).show()
            }
            else{
                mAuth.sendPasswordResetEmail(
                    edtgmail.text.toString()).addOnCompleteListener(this@ResetPassword){task ->
                    if(task.isSuccessful){
                        val user  = mAuth.currentUser
                        Toast.makeText(this@ResetPassword, "Reset Password Link is sent to your mail", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@ResetPassword, "Reset Password mail could not be sent", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}