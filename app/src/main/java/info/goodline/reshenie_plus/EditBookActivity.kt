package info.goodline.reshenie_plus

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_all_books.*
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)
        setSupportActionBar (toolbarEdit)
        toolbarEdit.setNavigationIcon(R.drawable.baseline_keyboard_backspace_white_24)
    }

    fun btnSave(view: View) {
        // вызывая конструктор Books, значения text из EditText присваеваем полям класса Books
        val books = Books (etNameBook.text.toString(), etDescribe.text.toString(), etLink.text.toString())
        val intent = Intent() // можно не указывать активити для запуска, setResult вернет объект intent в AllBooks (потому что startActivityForResult)
        intent.putExtra("newBook", books)
        setResult(Activity.RESULT_OK, intent)
        finish() // завершает работу этой активити
    }




//    toolbar.setNavigationIcon(R.drawable.my_icon);:
//
//    Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
//    setSupportActionBar(toolbar);
//    toolbar.setNavigationIcon(R.drawable.my_icon);
}

