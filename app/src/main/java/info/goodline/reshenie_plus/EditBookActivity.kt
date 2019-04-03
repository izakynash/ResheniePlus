package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)
    }

    fun btnSave(view: View) {
        val books = Books (etNameBook.text.toString(), etDescribe.text.toString(), etLink.text.toString())
        val intent = Intent(this, AllBooksActivity::class.java)
        intent.putExtra(EditBookActivity::class.java.simpleName, books)
        setResult(Activity.RESULT_OK)
        finish()
    }
}

