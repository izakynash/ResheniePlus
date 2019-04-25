package info.goodline.reshenie_plus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.models.Book
import info.goodline.reshenie_plus.models.BookRealm
import info.goodline.reshenie_plus.models.Chapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_chapter.*


class ChapterActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {

    var book: Book? = null

    private val dataBaseHelper = DataBaseHelper()

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

        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(BookRealm::class.java).equalTo("name", nameBook)
                .findFirst()
            book = realm.copyFromRealm(results)?.map2Data()
        }

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

            dataBaseHelper.saveBook(book)
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

