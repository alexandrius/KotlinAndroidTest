package com.turtlecat.usermanager.ui.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koushikdutta.ion.Ion
import com.turtlecat.usermanager.bean.User
import com.turtlecat.usermanager.R
import java.util.*
import kotlinx.android.synthetic.main.user_recycler_item.view.*

/**
 * Created by Alex on 4/24/2016.
 */

class UserRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var userList: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var v = LayoutInflater.from(parent?.context).inflate(R.layout.user_recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val isRemoved = userList.get(position).isRemoved
        if (!isRemoved) {
            holder?.itemView?.userItemName?.text = userList.get(position).name.first + " " + userList.get(position).name.first
            var context = holder?.itemView?.context
            Ion.with(context).load(userList.get(position).picture.large).asBitmap().setCallback { exception, bitmap ->
                holder?.itemView?.userItemAvatar?.setImageBitmap(bitmap)
            }
        } else {
            val params = holder?.itemView?.layoutParams
            params?.height = 0
            holder?.itemView?.layoutParams = params
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
