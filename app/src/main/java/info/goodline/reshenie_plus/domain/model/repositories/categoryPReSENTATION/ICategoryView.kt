package info.goodline.reshenie_plus.domain.model.repositories.categoryPReSENTATION

interface ICategoryView {
    fun showCardList() {} // здесь отобразить все данные
    fun showMessage(a: String) {}
    fun showProgressBar(boolean: Boolean) {}
}