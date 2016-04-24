package com.turtlecat.usermanager.bean

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by Alex on 4/24/2016.
 */

@PaperParcel
data class User(
        val gender: String,
        val name: Name,
        val location: Location,
        val email: String,
        val phone: String,
        val cell: String,
        val picture: Picture,
        val nat: String,
        var isRemoved: Boolean = false
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(User::class.java)
    }
}

@PaperParcel
data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(Picture::class.java)
    }
}

@PaperParcel
class Name(
        val title: String,
        val first: String,
        val last: String
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(Name::class.java)
    }
}

@PaperParcel
class Location(
        val street: String,
        val city: String,
        val state: String
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(Location::class.java)
    }
}

