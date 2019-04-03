package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AllBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
    }

   fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivity(intent)
    }
}