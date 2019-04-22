package info.goodline.reshenie_plus

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_category_list.*
import android.content.Intent
import android.util.Log
import info.goodline.reshenie_plus.models.Category

class CategoryActivity : AppCompatActivity(), AllBookAdapter.onItemClickListener {

//    private val bookNameArray: List<String> = Arrays.asList(
//        "Информатика", "Геометрия и инженерная графика"
//    )

    override fun onItemClick(nameItem: String?) {
        val intent = Intent()
        intent.putExtra("addCategory", nameItem)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        setSupportActionBar(toolbarCategory)
        toolbarCategory.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        Log.d(TAG, "onCreate")

        rvLayout.layoutManager = LinearLayoutManager(this)
        val dataBaseHelper = DataBaseHelper()
        rvLayout.adapter = CategoryAdapter(dataBaseHelper.categoryList, this)
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
