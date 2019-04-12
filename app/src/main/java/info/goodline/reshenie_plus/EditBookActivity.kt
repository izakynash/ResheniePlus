package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_book.*


class EditBookActivity: AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_CATEGORY = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)
        setSupportActionBar(toolbarEdit)
        toolbarEdit.setNavigationIcon(R.drawable.baseline_keyboard_backspace_white_24)
    }

    // "Когда пользователь выбирает пункт меню параметров, система вызывает метод onOptionsItemSelected()"
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    fun btnSave(view: View) {
        // вызывая конструктор Books , значения text из EditText присваеваем полям класса Books
        val books = Books(etNameBook.text.toString(), etDescribe.text.toString(), etLink.text.toString(), null)
        val intent = Intent() // можно не указывать активити для запуска, setResult вернет объект intent в AllBooks (потому что startActivityForResult)
        intent.putExtra("newBook", books)
        setResult(Activity.RESULT_OK, intent)
        Log.d(TAGs, "btnSave")
        finish() // завершает работу этой активити
    }

    fun btnCategory(view: View) {
        val intent = Intent(this, CategoryListActivity::class.java)
        startActivityForResult(intent, EditBookActivity.REQUEST_CODE_CATEGORY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK && requestCode == 1) {
            val category = data?.extras?.get("addCategory").toString()
            etCategory.setText(category)
        }
    }
}

