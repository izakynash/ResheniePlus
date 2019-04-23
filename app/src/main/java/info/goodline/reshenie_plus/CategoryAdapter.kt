package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.models.Category
import info.goodline.reshenie_plus.models.CategoryRealm
import info.goodline.reshenie_plus.models.Migration
import io.realm.Realm
import io.realm.RealmConfiguration

class CategoryAdapter(private val categoryList: MutableList<Category>, private val clickListener: CategoryActivity): RecyclerView.Adapter<CategoryAdapter.BookVH>() {

    override fun getItemCount() = categoryList.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
        Log.d(TAG, "Category_onCreateViewHolder")
        val itemViewCategory = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view_simple_list, viewGroup, false)
        return BookVH(itemViewCategory)
    }

    override fun onBindViewHolder(bookVH: BookVH, position: Int) {
        val categoryName = categoryList[position]
        bookVH.tvCategoryVH?.text = categoryName.name
        Log.d(TAG, "Category_onBindView")

        Realm.getDefaultInstance().use { realm2 ->
            realm2.beginTransaction()
            Log.d(TAG, "Realm.get1")
            // Важный момент - При использовании copyToRealmOrUpdate если записи в бд нет, то она добавится, если
            // по первичному ключу запись будет найдена, то realm обновит ее
            realm2.copyFromRealm(realm2.copyToRealmOrUpdate(categoryName.map2Realm()))
            Log.d(TAG, "Realm.get2 ${realm2.where(CategoryRealm::class.java).findAll()}")
            realm2.commitTransaction()
        }


//
//        val config = RealmConfiguration.Builder()
//            .name("myrealm.realm")
//            .schemaVersion(1)
//            .modules(CategoryRealm())
//            .migration(Migration())
//            .build()

//        val dataBaseHelper = DataBaseHelper()
//        dataBaseHelper.saveAllCategory(categoryName, config)

//        val mRealm = Realm.getDefaultInstance()
//        mRealm.beginTransaction()
//        val categoryRealm: CategoryRealm = mRealm.createObject(CategoryRealm::class.java)
//        categoryRealm.id = categoryName.id
//        categoryRealm.name = categoryName.name
//        mRealm.commitTransaction()

        bookVH.itemView.setOnClickListener {
            clickListener.onItemClick(categoryName.name)
        }
    }

    class BookVH (itemViewCategory: View?): RecyclerView.ViewHolder(itemViewCategory) {
        var tvCategoryVH: TextView? = null
        init {
            tvCategoryVH = itemViewCategory?.findViewById(R.id.tvNameItem)
        }
    }
}