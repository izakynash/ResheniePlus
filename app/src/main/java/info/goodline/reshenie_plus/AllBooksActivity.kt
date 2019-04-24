package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.models.BookRealm
import info.goodline.reshenie_plus.models.CategoryRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_all_books.*
import java.util.*



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

    var bookList = DataBaseHelper.bookList

    var booksArray: MutableList<Books?> = mutableListOf(
        Books(1,
            "Информатика: Теория, вычисления, программирование",
            "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских",
            "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf",
            R.drawable.book1
        ),
        Books(2,
            "Информатика. Программирование в системе Turbo  Pascal",
            "Практикум / Г.Е. Иванец, О.А. Ивина; Кемеровский технологический институт пищевой промышленности",
            "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii106.pdf",
            R.drawable.book2
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(tbAllBooks)

        Realm.init(this)

        dataBaseHelper.saveBook(bookList[0])
        dataBaseHelper.saveBook(bookList[1])
        
        Log.d(TAG, "onCreate1")

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        //rvAllBooks.adapter = AllBookAdapter(booksArray, this)
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
            //val book: Books? = data?.extras?.getParcelable<Books>("newBook")

            var id = data?.extras?.getInt("newBook")
//            dataBaseHelper.saveBook(bookList[0])

    //        dataBaseHelper.loadBooks()[2]

            Realm.getDefaultInstance().use { realm ->
                realm.beginTransaction()
                Log.d(TAG, "Realm.getDefaultInstance ${realm.where(BookRealm::class.java).findAll()}")
                realm.commitTransaction()
            }


            bookList.add(2, dataBaseHelper.loadBooks()[2])
            rvAllBooks.adapter.notifyItemInserted(2)

            //dataBaseHelper.saveBook(book)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

