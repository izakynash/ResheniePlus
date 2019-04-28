package info.goodline.reshenie_plus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.goodline.reshenie_plus.Providers.BookDBProvider
import info.goodline.reshenie_plus.Providers.CategoryDBProvider
import info.goodline.reshenie_plus.Providers.ChapterDBProvider
import info.goodline.reshenie_plus.models.Book
import info.goodline.reshenie_plus.models.Chapter
import kotlinx.android.synthetic.main.activity_chapter.*

class ChapterActivity : AppCompatActivity(), ChapterAdapter.onItemClickListener {

    private val bookProvider = BookDBProvider()

    var book: Book? = null

    override fun onItemClick(nameItem: String?) {
        val intent = Intent(this, TaskActivity::class.java)
        intent.putExtra("nameBook", tvNameBookUp.text.toString())
        intent.putExtra("nameChapter", nameItem)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        setSupportActionBar(tbChapter)
        tbChapter.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        val nameBook = intent?.extras?.get("nameBook").toString()
        tvNameBookUp.text = nameBook

        book = bookProvider.loadBookByName(nameBook)

        rvChapter.layoutManager = LinearLayoutManager(this)
        rvChapter.adapter = ChapterAdapter(book?.chapters, this)
    }

    fun btnAddChapter(view: View) {
        if (etNameNewChapter.text.toString() == "")
            Toast.makeText(this, "Введите название главы", Toast.LENGTH_SHORT).show()
        else {
            val chapter = Chapter(name = etNameNewChapter.text.toString())

            etNameNewChapter.text = null

            val adapter = rvChapter.adapter as ChapterAdapter
            adapter.insertItem(chapter)

            bookProvider.saveBook(book)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

