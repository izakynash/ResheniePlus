package info.goodline.reshenie_plus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_all_books.*
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.activity_edit_book.*
import java.util.*
import android.content.Intent



class CategoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        setSupportActionBar(toolbarCategory)
        toolbarCategory.setNavigationIcon(R.drawable.baseline_keyboard_backspace_white_24)

        val bookNameArray: List<String> = Arrays.asList(
            "Информатика", "Геометрия и инженерная графика")

        rvLayout.layoutManager = LinearLayoutManager (this)
        rvLayout.adapter = BookAdapter(bookNameArray)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> finish()
            /*R.id.action_search -> {
                val intent = Intent(this, SearchUsersActivity::class.java)
                startActivity(intent)
            } */
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class BookAdapter(private val bookNameArray: List<String>): RecyclerView.Adapter<BookAdapter.BookVH>() {

        override fun getItemCount() = bookNameArray.size

        // создает новые объекты ViewHolder, сколько потребуется
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
            val itemViewCategory = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_category, viewGroup, false)
            return BookVH(itemViewCategory)
        }

        // наполняет представления из ViewHolder
        override fun onBindViewHolder(bookVH: BookVH, position: Int) {
            val categoryName: String = bookNameArray[position]
            bookVH.tvCategoryVH?.text = categoryName
        }

        class BookVH (itemViewCategory: View?): RecyclerView.ViewHolder(itemViewCategory) {
            var tvCategoryVH: TextView? = null

            init {
                tvCategoryVH = itemViewCategory?.findViewById(R.id.tvNameCategory)
            }
        }
    }
}
