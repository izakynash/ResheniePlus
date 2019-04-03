package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_all_books.*

class AllBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(toolbar);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.all_books_menu, menu)
        return true
}

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java)
        startActivity(intent)
   }


}