package info.goodline.reshenie_plus

import android.content.Context
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
import android.util.Log
import android.widget.Toast

val TAGer: String = "LOOK"
class CategoryListActivity : AppCompatActivity(), BookAdapter.onItemClickListener {

    override fun onItemClick(item: String) {
        Toast.makeText(this, item, Toast.LENGTH_LONG).show()
        Log.d(TAGer, "onItemClick")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        setSupportActionBar(toolbarCategory)
        toolbarCategory.setNavigationIcon(R.drawable.baseline_keyboard_backspace_white_24)

        Log.d(TAGer, "onCreate")

        val bookNameArray: List<String> = Arrays.asList(
            "Информатика", "Геометрия и инженерная графика"
        )

//        Теперь нам надо написать реализацию обработки кликов на элементы в нашей Activity.
//        Для начала заимплементим интерфейс из адаптера

        rvLayout.layoutManager = LinearLayoutManager(this)
        rvLayout.adapter = BookAdapter(bookNameArray, this)
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
}

    class BookAdapter(private val bookNameArray: List<String>, private val clickListener: onItemClickListener): RecyclerView.Adapter<BookVH>() {

        interface onItemClickListener {
            fun onItemClick(item: String)
        }

        //Далее создаем переменную, в которую будем сетить Activity, реализующую обработку нажатий.
        // Таким образом, обработкой нажатий будет заниматься не адаптер, а Activity.
        // Сетить эту переменную будем через первичный конструктор адаптера

        override fun getItemCount() = bookNameArray.size

        // создает новые объекты ViewHolder, сколько потребуется
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
            val itemViewCategory = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_category, viewGroup, false)
            return BookVH(itemViewCategory)
        }

        // наполняет представления из ViewHolder
        // В этом же адаптере мы должны повесить обработчик нажатий на элементы списка.
        override fun onBindViewHolder(bookVH: BookVH, position: Int) {
            val categoryName: String = bookNameArray[position]
            bookVH.tvCategoryVH?.text = categoryName
            Log.d(TAGer, "onBindView")
            bookVH.itemView.setOnClickListener(View.OnClickListener() {
                @Override
                fun onClick(view: View) {
                    clickListener.onItemClick(categoryName)
                }
            }
            )
        }

//        public ChatVH(@NonNull View itemView) {
//
//            super(itemView);
//
//            ivAvatar = itemView.findViewById(R.id.ivAvatar);
//
//            tvChatTheme = itemView.findViewById(R.id.tvChatTheme);
//
//            tvUserName = itemView.findViewById(R.id.tvUserName);
//
//            tvMessage = itemView.findViewById(R.id.tvMessage);

    }

        class BookVH (itemViewCategory: View?): RecyclerView.ViewHolder(itemViewCategory) {
            var tvCategoryVH: TextView? = null

            init {
                tvCategoryVH = itemViewCategory?.findViewById(R.id.tvNameCategory)
            }
        }


