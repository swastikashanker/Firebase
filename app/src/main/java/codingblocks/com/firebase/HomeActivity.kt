package codingblocks.com.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

import android.util.Log

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :AppCompatActivity() {


    private val booksList = ArrayList<Book>()
    lateinit var bookAdapter: BookAdapter

    // lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        bookAdapter = BookAdapter(booksList)

        rvBooks.layoutManager = LinearLayoutManager(this)
        rvBooks.adapter = bookAdapter


//


        val dbref = FirebaseDatabase.getInstance().getReference("Books")

        Log.e("TAG", "IS $dbref")



        dbref.addChildEventListener(object : ChildEventListener {


            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {



                val book = dataSnapshot.getValue(Book::class.java)

                Log.e("book", "book is $book")
                booksList.add(book!!)

                bookAdapter.notifyDataSetChanged()

//                FirebaseDatabase.getInstance().reference.child("users")
//                    .child(book.uid)
//                    .addListenerForSingleValueEvent(object :ValueEventListener{
//                        override fun onDataChange(p0: DataSnapshot) {
//                            val user = p0.getValue(User::class.java)
//                             = user
//                        }
//
//                        override fun onCancelled(p0: DatabaseError) {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }
//
//                    })


                    }


            override fun onChildRemoved(p0: DataSnapshot) {

            }


            override fun onCancelled(p0: DatabaseError) {


                Log.w("tag", "loadPost:onCancelled", p0.toException())

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

        })



        btnAdd.setOnClickListener {

            val mi = Intent(this, MainActivity::class.java)
            mi.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(mi)
        }








    }








}


