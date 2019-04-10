package info.goodline.reshenie_plus

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_all_books.*
import kotlinx.android.synthetic.main.activity_category_list.*
import java.util.*

val TAGs: String = "solo"


class AllBooksActivity : AppCompatActivity() {

   companion object {
       const val REQUEST_CODE_EDIT_BOOK = 1
   }

    val booksArray: List<Books> = Arrays.asList(
        Books("Информатика: Теория, вычисления, программирование",
            "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских\nВ.В. Романова и др\"",
            "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf"),
        Books("Информатика. Программирование в системе Turbo  Pascal",
            "Практикум / Г.Е. Иванец, О.А. Ивина; Кемеровский технологический институт пищевой промышленности",
            "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii106.pdf"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)
        setSupportActionBar(toolbar)

        rvAllBooks.layoutManager = LinearLayoutManager(this)
        rvAllBooks.adapter = AllBookAdapter(booksArray)

        Log.d(TAGs, "onCreate")

        // book3Layout.visibility = View.INVISIBLE
    }

    fun btnEditBook(view: View) {
        val intent = Intent(this, EditBookActivity::class.java) // intent с явным указанием на активити
        startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK) // после запуска активити требуется получить результат, поэтому ForResult
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if(resultCode == Activity.RESULT_OK && requestCode == 1) {
                Log.d(TAGs, "onActivityResult")
                val book = data?.extras?.getParcelable<Books>("newBook")

                val adapter = AllBookAdapter(booksArray)
                adapter.insertItem(book)

            }
    }

    class AllBookAdapter(private val booksArray: List<Books?>): RecyclerView.Adapter<AllBooksVH>() {

        fun insertItem(item: Books?) {
            Log.d(TAGs, "insertItem")
            booksArray.plusElement(item)

            Log.d(TAGs, "insertItem ${item?.name}")
            Log.d(TAGs, "insertItem ${booksArray[1]?.link}")
            notifyItemInserted(itemCount) // сообщить адаптеру, что данные изменились
           // Log.d(TAGs, "itemCount = $itemCount")
        }

        override fun getItemCount() = booksArray.size

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): AllBooksVH {
            Log.d(TAGs, "onCreateViewHolder")
            val itemViewBooks = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_books, viewGroup, false)
            return AllBooksVH(itemViewBooks)
        }

        override fun onBindViewHolder(allBooksVH: AllBooksVH, position: Int) {
            val book: Books? = booksArray[position]

            allBooksVH.tvNameBookVH?.text = book?.name
            allBooksVH.tvDescribeVH?.text = book?.describe
            allBooksVH.tvLinkVH?.text = book?.link

                Log.d(TAGs, "onBindView")
        }
    }

    class AllBooksVH (itemViewBooks: View?): RecyclerView.ViewHolder(itemViewBooks) {
        var tvNameBookVH: TextView? = null
        var tvDescribeVH: TextView? = null
        var tvLinkVH: TextView? = null
       // var ivImageVH: ImageView? = null
        init {
            tvNameBookVH = itemViewBooks?.findViewById(R.id.tvNameBook)
            tvDescribeVH = itemViewBooks?.findViewById(R.id.tvDescribe)
            tvLinkVH = itemViewBooks?.findViewById(R.id.tvLink)
          //  ivImageVH = itemViewBooks?.findViewById(R.id.ivImage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.all_books_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}