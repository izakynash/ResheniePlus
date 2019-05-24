package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import info.goodline.reshenie_plus.Providers.CategoryDBProvider
import info.goodline.reshenie_plus.domain.model.repositories.categoryPReSENTATION.CategoryListPresenter
import info.goodline.reshenie_plus.domain.model.repositories.categoryPReSENTATION.ICategoryView
import kotlinx.android.synthetic.main.activity_category_list.*


class CategoryActivity : AppCompatActivity(), CategoryAdapter.onItemClickListener, ICategoryView {

    lateinit var presenter

    // здесь будут методы из интрефейса, мы их переопределяем и пишем в них логику

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

        presenter = CategoryListPresenter()
        presenter = this



        rvLayout.layoutManager = LinearLayoutManager(this)
        // наверное, нужно запустить ажапетр из Presenter
        rvLayout.adapter = CategoryAdapter(CategoryDBProvider.getAllCategory(), this)
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
