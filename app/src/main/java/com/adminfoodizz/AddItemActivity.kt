package com.adminfoodizz

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.adminfoodizz.databinding.ActivityAddItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddItemActivity : AppCompatActivity() {

    private lateinit var foodName:String
    private lateinit var foodPrice:String
    private lateinit var foodDescription:String
    private lateinit var foodIngredient:String
    private  var foodImageUri: Uri?=null


    private lateinit var auth:FirebaseAuth
    private lateinit var databse:FirebaseDatabase
    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        databse = FirebaseDatabase.getInstance()

        binding.AddItembtn.setOnClickListener {
            foodName = binding.enterFoodName.text.toString().trim()
            foodPrice = binding.enterFoodPrice.text.toString().trim()
            foodDescription = binding.description.text.toString().trim()
            foodIngredient = binding.ingredent.text.toString().trim()

            if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredient.isBlank())) {
                uploadData()
                Toast.makeText(this, "Item Add Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show()
            }
        }
        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")

        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun uploadData() {
        val menuRef = databse.getReference("menu")
        val newItemKey :String? = menuRef.push().key
        if(foodImageUri!=null){
            val storageRef:StorageReference =FirebaseStorage.getInstance().reference
            val imageRef:StorageReference=storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    val newItem = AllMenu(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredient = foodIngredient,
                        foodImage =  downloadUrl.toString(),
                    )
                    newItemKey?.let { key->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            menuRef.child(key).setValue(newItem).addOnSuccessListener {
                                Toast.makeText(this,"Data Upload Successfully",Toast.LENGTH_SHORT).show()
                            }
                                .addOnFailureListener{
                                    Toast.makeText(this,"Data Upload Failed",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
            }.addOnFailureListener{
                Toast.makeText(this,"Image Upload failed",Toast.LENGTH_SHORT).show()
            }

        }else{
                Toast.makeText(this,"Please Select an image",Toast.LENGTH_SHORT).show()
        }
    }

   private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if (uri != null){
            binding.selectedImage.setImageURI(uri)
            foodImageUri = uri
        }
    }
}