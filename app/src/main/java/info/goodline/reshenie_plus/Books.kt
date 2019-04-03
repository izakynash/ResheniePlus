package info.goodline.reshenie_plus

import android.os.Parcel
import android.os.Parcelable


class Books (val name: String?, val describe: String?, val link: String?) : Parcelable {

    constructor (`in`: Parcel): this (`in`.readString(),`in`.readString(),`in`.readString() )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(describe)
        parcel.writeString(link)
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
