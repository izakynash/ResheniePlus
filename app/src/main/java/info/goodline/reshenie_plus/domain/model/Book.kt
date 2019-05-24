package info.goodline.reshenie_plus.domain.model

import android.os.Parcel
import android.os.Parcelable

class Book (var id: Long = 0,
            var name: String?,
            var describtion: String?,
            var link: String?,
            var image: Int = 0,
            var chapters: MutableList<Chapter>? = null): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(describtion)
        parcel.writeString(link)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}

