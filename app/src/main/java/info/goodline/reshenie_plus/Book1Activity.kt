package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Book1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book1)
    }

    fun btnSolved(view: View) {
        val intent = Intent(this, SolvedActivity::class.java)
        startActivity(intent)
    }


}
