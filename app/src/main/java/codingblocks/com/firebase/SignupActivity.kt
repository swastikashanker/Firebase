package codingblocks.com.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btnRegister.setOnClickListener {




            val username=etName.text.toString()
            val address=etAddress.text.toString()
            val phone=etPhone.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()



            if(email.isEmpty()||password.isEmpty()){
               Toast.makeText(this,"Please enter email id and password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
           }
            Log.e("Tag", "Email is ${email}")





            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)




                .addOnSuccessListener {



                val uid=FirebaseAuth.getInstance().currentUser!!.uid
                    val uref= FirebaseDatabase.getInstance().getReference("Users")


                    val user=User(username,address,phone)

                    uref.child(uid).setValue(user).addOnCompleteListener {

                        Toast.makeText(this,"Contact Info saved",Toast.LENGTH_SHORT).show()



                        val hi=Intent(this,MainActivity::class.java)
                        Log.e("intent","starting mainact")
                        startActivity(hi)


                    }

                }
                .addOnCompleteListener {

                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.e("signup","created uid:${it.result?.user?.uid}")



                }


                .addOnFailureListener {
                    Log.e("fail","Failed to create")
                }


        }


        tvreg.setOnClickListener {

            val li= Intent(this,LoginActivity::class.java)
            startActivity(li)

        }




        }








    }


