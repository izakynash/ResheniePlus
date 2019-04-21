package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.activity_task.*
import java.util.*

class TaskActivity : AppCompatActivity() {

    private val taskNumberArray: List<String> = Arrays.asList(
        "1", "2", "3"
    )

//    override fun onItemClick(nameItem: String?) {
//        val intent = Intent(this, EditBookActivity::class.java) // исправить на таск активити
//        intent.putExtra("nameChapter", nameItem)
//    }

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

        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = TaskAdapter(taskNumberArray)
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
