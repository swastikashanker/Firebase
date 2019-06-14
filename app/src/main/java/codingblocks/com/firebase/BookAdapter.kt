package codingblocks.com.firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.item_row.view.*


class BookAdapter(private val books: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.BookHolder>() {


    lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): BookHolder {
        context = viewGroup.context
        return BookHolder(LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false))
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(bookHolder: BookHolder, position: Int) {
        val currentBook = books[position]

        bookHolder.itemView.tvTitle.text = currentBook.title
        bookHolder.itemView.tvAuthor.text = currentBook.author
        bookHolder.itemView.tvBuy.text = currentBook.buyPrice
        bookHolder.itemView.tvRent.text = currentBook.rentPrice
        bookHolder.itemView.tvuid.text=currentBook.uid




    }


        inner class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }

