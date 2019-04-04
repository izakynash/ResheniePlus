package info.goodline.reshenie_plus

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_all_books.*

class AllBooksActivity : AppCompatActivity() {

   companion object {
       const val REQUEST_CODE_EDIT_BOOK = 1
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(toolbar)
        book3Layout.visibility = View.INVISIBLE
    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java) // intent с явным указанием на активити
        startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK) // после запуска активити требуется получить результат, поэтому ForResult
    }

    // по завершении работы активити EditBook вернет результат через SetResult в метод onActivityResult():
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // по requestCode определяем, с какого активити пришел результат, в данный момент смысла не имеет
        // в data приходит объект класса Intent, переданный через setResult

            if(resultCode == Activity.RESULT_OK && requestCode == 1) {

                book3Layout.visibility = View.VISIBLE

                // поля, переданные из PutExtra, читаются как объект типа Bundle, getParcelable - метод класса Bundle, вернет объект типа Books
                val book = data?.extras?.getParcelable<Books>("newBook") // в объект book записываем поля, переданные через putExtra и извлеченные из Parcel

                tv3NameBook.text = book?.name
                tv3MiniDescription.text = book?.describe
                tv3Link.text = book?.link
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.all_books_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}