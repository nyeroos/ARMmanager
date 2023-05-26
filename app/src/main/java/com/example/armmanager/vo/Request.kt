package com.example.armmanager.vo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "request_table")
class Request(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "request_number")
    val number: Int,

    @ColumnInfo(name = "request_name")
    val name: String,

    @ColumnInfo(name = "customer")
    val customer: String,

    @ColumnInfo(name = "expected_date")
    val expectedDate: String,

    @ColumnInfo(name = "creation_date")
    val creationDate: String,

    @ColumnInfo(name = "actual_date")
    val actualDate: String,

    @ColumnInfo(name = "user")
    val user: Int,

    @ColumnInfo(name = "status")
    val status: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(number)
        parcel.writeString(name)
        parcel.writeString(customer)
        parcel.writeString(expectedDate)
        parcel.writeString(creationDate)
        parcel.writeString(actualDate)
        parcel.writeInt(user)
        parcel.writeString(status)
    }

    companion object CREATOR : Parcelable.Creator<Request> {
        override fun createFromParcel(parcel: Parcel): Request {
            return Request(
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readInt(),
                parcel.readString()!!
            )
        }

        override fun newArray(size: Int): Array<Request?> {
            return arrayOfNulls(size)
        }
    }
}
