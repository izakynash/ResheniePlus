package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_edit_book.*


class EditBookActivity: AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_CATEGORY = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)
        setSupportActionBar(toolbarEdit)
        toolbarEdit.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    fun btnSave(view: View) {
        if (etNameBook.text.toString() == "" || etDescribe.text.toString() == ""  || etLink.text.toString() == "" || etCategory.text.toString() == "")
            Toast.makeText(this, "Заполнены не все поля", LENGTH_SHORT).show()
        else {
            val books = Books(1, etNameBook.text.toString(), etDescribe.text.toString(), etLink.text.toString(), null)
            val intent = Intent()
            intent.putExtra("newBook", books)
            setResult(Activity.RESULT_OK, intent)
            Log.d(TAG, "btnSave")
            finish()
        }
    }

    fun btnCategory(view: View) {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivityForResult(intent, EditBookActivity.REQUEST_CODE_CATEGORY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == 1) {
            val category = data?.extras?.get("addCategory").toString()
            etCategory.setText(category)
        }
    }
}

