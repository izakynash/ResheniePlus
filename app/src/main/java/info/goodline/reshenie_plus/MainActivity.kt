package info.goodline.reshenie_plus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnBook1(view: View) {
        val intent = Intent(this, Book1Activity::class.java)
        startActivity(intent)
    }

}