package codingblocks.com.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {

            loginuser()



        }


        tvlog.setOnClickListener {


            val si= Intent(this,SignupActivity::class.java)

            startActivity(si)


        }

    }



    private fun loginuser(){

        val email = etEmaillog.text.toString()
        val password = etPasswordlog.text.toString()



        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"Please enter email id and password", Toast.LENGTH_SHORT).show()
            return
        }


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)


            .addOnSuccessListener {

                val hi=Intent(this,MainActivity::class.java)
                hi.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(hi)
            }
            .addOnCompleteListener {

                if (!it.isSuccessful) return@addOnCompleteListener

                Log.e("login","created uid:${it.result?.user?.uid}")
            }


            .addOnFailureListener {
                Log.e("fail","Failed to login")
            }





    }
}