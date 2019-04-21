package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_all_books.*
import java.util.*

const val TAG = "LOOK"
class AllBooksActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {


    override fun onItemClick(nameItem: String?) {
        Log.d(TAG, "AllBooks_onItemClick")
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra("nameBook", nameItem)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_CODE_EDIT_BOOK = 1
    }

    var booksArray: List<Books?> = Arrays.asList(
        Books(
            "Информатика: Теория, вычисления, программирование",
            "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских",
            "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf",
            R.drawable.book1
        ),
        Books(
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

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        rvAllBooks.adapter = AllBookAdapter(booksArray, this)

        Log.d(TAG, "onCreate")
    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Log.d(TAG, "onActivityResult")
            val book: Books? = data?.extras?.getParcelable<Books>("newBook")

            booksArray = booksArray.plus(book)
            rvAllBooks.adapter = AllBookAdapter(booksArray, this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

