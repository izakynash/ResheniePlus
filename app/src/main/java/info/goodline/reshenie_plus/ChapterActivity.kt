package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.goodline.reshenie_plus.models.Chapter
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.activity_edit_book.*
import java.util.*

class ChapterActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {

//    private val chapterNameArray: List<String> = ArraaList(
//    )

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
        Log.d(TAG, "Chapter_onCreate")

        val nameBook = intent?.extras?.get("nameBook").toString()
        tvNameBookUp.text = nameBook

        rvChapter.layoutManager = LinearLayoutManager(this)
        rvChapter.adapter = ChapterAdapter(DataBaseHelper.chapterList, this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()

            /*R.id.action_search -> {
                val intent = Intent(this, SearchUsersActivity::class.java)
                startActivity(intent)
            } */
        }
        return super.onOptionsItemSelected(item)
    }

 override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_account, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun btnAddChapter(view: View) {
        var chapterList = DataBaseHelper.chapterList
       chapterList

        if (etNameNewChapter.text.toString() == "")
            Toast.makeText(this, "Введите название главы", Toast.LENGTH_SHORT).show()
        else {
            val chapter = Chapter(1, etNameNewChapter.text.toString())
            val intent = Intent()
            intent.putExtra("newBook", books)
            setResult(Activity.RESULT_OK, intent)
            Log.d(TAG, "btnSave")
            finish()
        }
    }

        val intent = Intent(this, EditBookActivity::class.java)
        startActivityForResult(intent, AllBooksActivity.REQUEST_CODE_EDIT_BOOK)
    }
}
