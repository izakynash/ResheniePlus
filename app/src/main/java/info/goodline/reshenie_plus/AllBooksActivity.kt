package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import info.goodline.reshenie_plus.Providers.BookDBProvider
import info.goodline.reshenie_plus.Providers.CategoryDBProvider
import info.goodline.reshenie_plus.models.Book
import kotlinx.android.synthetic.main.activity_all_books.*

const val TAG = "LOOK"
class AllBooksActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {

    private val bookProvider = BookDBProvider()

    private val loadBooks =  bookProvider.loadBooks()

    companion object {
        const val REQUEST_CODE_EDIT_BOOK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(tbAllBooks)


        if (loadBooks.isNotEmpty()) tvNoBooks.visibility = View.INVISIBLE

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        rvAllBooks.adapter = AllBookAdapter(loadBooks, this)
    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {

            tvNoBooks.visibility = View.INVISIBLE
            val book = data?.extras?.getParcelable<Book>("newBook")
            bookProvider.saveBook(book)

            val adapter = rvAllBooks.adapter as AllBookAdapter
            adapter.insertItem(book)
        }
    }

    override fun onItemClick(nameItem: String?) {
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra("nameBook", nameItem)
        startActivity(intent)
    }

    override fun onItemDelete(nameBook: String?, position: Int) {
        bookProvider.deleteBook(nameBook)

        val adapter = rvAllBooks.adapter as AllBookAdapter
        adapter.removeAt(position)

        if (loadBooks.isEmpty()) tvNoBooks.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }

}

//        очистить бд
//        val realm = Realm.getDefaultInstance()
//        realm.executeTransaction { realm -> realm.delete(BookRealm::class.java) }

//      сбросить конфигурацию
//        val config = RealmConfiguration.Builder()
//         .deleteRealmIfMigrationNeeded()
//         .build()
//       Realm.setDefaultConfiguration(config)


