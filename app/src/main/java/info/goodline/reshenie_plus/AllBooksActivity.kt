package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.View
import info.goodline.reshenie_plus.models.Book
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_all_books.*


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

    // var bookList: MutableList<Book?> = DataBaseHelper.bookList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(tbAllBooks)

        Realm.init(this)

        // сбросить конфигурацию
//        val config = RealmConfiguration.Builder()
//         .deleteRealmIfMigrationNeeded()
//            .build()
//       Realm.setDefaultConfiguration(config)

        // очистить бд
//               val realm = Realm.getDefaultInstance()
//        realm.executeTransaction { realm -> realm.delete(BookRealm::class.java) }

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        rvAllBooks.adapter = AllBookAdapter(dataBaseHelper.loadBooks(), this)

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

