package codingblocks.com.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){




    private fun verify(){

        val uid=FirebaseAuth.getInstance().uid
        if(uid==null){


            val si= Intent(this,SignupActivity::class.java)

            si.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(si)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId){
            R.id.menu_sign_out ->{

                FirebaseAuth.getInstance().signOut()

                val li= Intent(this,LoginActivity::class.java)

                li.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(li)

            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.nav_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }





    private fun writeNewUser(){

        val title= etTitle.text.toString()
        val author=etAuthor.text.toString()
        val priceBuy=etBuy.text.toString()
        val priceRent=etRent.text.toString()

        val dbref=FirebaseDatabase.getInstance().getReference("Books")
        val id=dbref.push().key
        val uid=FirebaseAuth.getInstance().currentUser!!.uid

        val book=Book(uid,title,author,priceBuy,priceRent)

        dbref.child(id!!).setValue(book).addOnCompleteListener {

            Toast.makeText(this,"Details saved",Toast.LENGTH_SHORT).show()


        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        verify()

        btnSave.setOnClickListener {

            FirebaseApp.initializeApp(this)


            writeNewUser()

            notification()

//            val intent = Intent(this, HomeActivity::class.java)
//
//            startActivity(intent)

        }

      btnNext.setOnClickListener {

          val intent = Intent(this, HomeActivity::class.java)
          intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                      startActivity(intent)


      }



    }

    private fun notification(){


        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(
                    "first",
                    "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )

            nm.createNotificationChannel(
                NotificationChannel(
                    "second",
                    "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }


        val simpleNotification = NotificationCompat.Builder(baseContext, "first")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentTitle("Book on Rent")
            .setContentText("New Book Added!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        nm.notify(System.currentTimeMillis().toInt(), simpleNotification)
    }


}
