package info.goodline.reshenie_plus

import android.os.Parcel
import android.os.Parcelable
import info.goodline.reshenie_plus.models.Chapter


class Books (var id: Int, var name: String?, var describe: String?, var link: String?, var image: Int?, var Chapters: MutableList<Chapter>? = null) : Parcelable {

    constructor (`in`: Parcel): this (`in`.readInt(), `in`.readString(),`in`.readString(),`in`.readString(), `in`.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(describe)
        parcel.writeString(link)
        parcel.writeValue(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Books> {
        override fun createFromParcel(parcel: Parcel): Books {
            return Books(parcel)
        }

        override fun newArray(size: Int): Array<Books?> {
            return arrayOfNulls(size)
        }
    }
}
