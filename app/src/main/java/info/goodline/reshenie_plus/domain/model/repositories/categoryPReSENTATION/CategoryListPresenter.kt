package info.goodline.reshenie_plus.domain.model.repositories.categoryPReSENTATION

import info.goodline.reshenie_plus.domain.model.repositorie.CategoryRepository


// презентер говорит вьюшке...
class CategoryListPresenter {

    var view: ICategoryView? = null

    var repository: CategoryRepository? = null



    fun attachView() {
        // здесь


    }



}