package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.*
import info.goodline.reshenie_plus.extensions.map2DataList
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.models.Book
import info.goodline.reshenie_plus.models.BookRealm
import info.goodline.reshenie_plus.models.CategoryRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_all_books.*
import java.util.*
import io.realm.RealmResults





const val TAG = "LOOK"
class AllBooksActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {

    val dataBaseHelper = DataBaseHelper()

    override fun onItemClick(nameItem: String?) {
        Log.d(TAG, "AllBooks_onItemClick")
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra("nameBook", nameItem)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_CODE_EDIT_BOOK = 1
    }

    var bookList: MutableList<Book?> = DataBaseHelper.bookList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(tbAllBooks)

        Realm.init(this)

        val realm = Realm.getDefaultInstance()
      //  realm.executeTransaction { realm -> realm.delete(BookRealm::class.java) }

        dataBaseHelper.saveBook(bookList[0])
        dataBaseHelper.saveBook(bookList[1])
        
        Log.d(TAG, "onCreate1")

        bookList = dataBaseHelper.loadBooks()

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        rvAllBooks.adapter = AllBookAdapter(bookList, this)

        Log.d(TAG, "onCreate")
    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Log.d(TAG, "onActivityResult")

            val book = data?.extras?.getParcelable<Book>("newBook")
            book?.id = bookList.size

            dataBaseHelper.saveBook(book)
            val adapter = rvAllBooks.adapter as AllBookAdapter
            adapter.insertItem(book)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

