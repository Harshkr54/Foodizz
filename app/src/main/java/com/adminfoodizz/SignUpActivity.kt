package com.adminfoodizz


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adminfoodizz.databinding.ActivitySignUpBinding
import com.adminfoodizz.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameOfResturant: String
    private lateinit var database: DatabaseReference
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //intialization Firebase Auth
        auth = Firebase.auth
        //intialization Database Auth
        database = Firebase.database.reference

        binding.CreateBtn.setOnClickListener {
            userName = binding.name.text.toString().trim()
            nameOfResturant = binding.restorentName.text.toString().trim()
            email = binding.emailOrPhone.text.toString().trim()
            password = binding.Password.text.toString().trim()

            if(userName.isBlank()|| nameOfResturant.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }
        }
        binding.AlreadyhaveAccountbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val locationList = arrayOf("Jharkhand", "Bihar", "Delhi", "Uttar Pardesh")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()

                Log.d("Account","createAccount : Failure",task.exception)
            }
        }
    }

    private fun saveUserData() {
        userName = binding.name.text.toString().trim()
        nameOfResturant = binding.restorentName.text.toString().trim()
        email = binding.emailOrPhone.text.toString().trim()
        password = binding.Password.text.toString().trim()
        val user = UserModel(userName,nameOfResturant,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }


}