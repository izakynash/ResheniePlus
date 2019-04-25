package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.models.*
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.activity_task.*
import java.util.*

class TaskActivity : AppCompatActivity() {

//    private val taskNumberArray: List<String> = Arrays.asList(
//        "1", "2", "3"
//    )

    var chapter: Chapter? = null

    private val dataBaseHelper = DataBaseHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(tbTask)
        tbTask.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        Log.d(TAG, "Task_onCreate")

        val nameBook = intent?.extras?.getString("nameBook")
        val nameChapter =  intent?.extras?.getString("nameChapter")

        tvTaskNameBookUpTask.text = nameBook
        tvNameСhapterUpTask.text = nameChapter

        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(ChapterRealm::class.java).equalTo("name", nameChapter)
                .findFirst()
            chapter = realm.copyFromRealm(results)?.map2Data()
        }

        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = TaskAdapter(chapter?.tasks)
    }

    fun btnAddTask(view: View) {
        if (etNumberNewTask.text.toString() == "")
            Toast.makeText(this, "Введите номер задачи", Toast.LENGTH_SHORT).show()
        else {
            val task = Task(number = etNumberNewTask.text.toString().toInt())

            etNumberNewTask.text = null

            val adapter = rvTask.adapter as TaskAdapter
            adapter.insertItem(task)

            dataBaseHelper.saveChapter(chapter)
        }
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
}
