package com.github.android.functionality.repolist.model

import android.os.Parcel
import android.os.Parcelable

data class Repository(

        var author: String,
        var name: String,
        var url: String,
        var description: String,
        var language: String,
        var stars: String,
        var forks: String,
        var currentPeriodStars: String

): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(language)
        parcel.writeString(stars)
        parcel.writeString(forks)
        parcel.writeString(currentPeriodStars)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }
}