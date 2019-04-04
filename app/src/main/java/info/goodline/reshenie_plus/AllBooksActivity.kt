package info.goodline.reshenie_plus

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
       const val REQUEST_CODE_BOOK = 2
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(toolbar)
        book3Layout.visibility = View.INVISIBLE

    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_BOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if(resultCode == Activity.RESULT_OK) {

                book3Layout.visibility = View.VISIBLE

                val arguments = intent.extras ?: return
                // похоже, приходит null (хз почему), поэтому здесь тело if завершается
                val book = arguments.getParcelable<Books>("newBook") ?: return

                tv3NameBook.text = book.name
                tv3MiniDescription.text = book.describe
                tv3Link.text = book.link
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.all_books_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}