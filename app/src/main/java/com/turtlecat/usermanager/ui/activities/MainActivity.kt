package com.turtlecat.usermanager.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.turtlecat.usermanager.R
import com.turtlecat.usermanager.bean.User
import com.turtlecat.usermanager.ui.adapters.UserRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val requestCode = 5
    lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        adapter = UserRecyclerViewAdapter()
        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(SimpleTouchHelperCallBack())
        itemTouchHelper.attachToRecyclerView(userRecyclerView)
    }

    inner class SimpleTouchHelperCallBack : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            var pos = viewHolder!!.adapterPosition
            adapter.userList.get(pos).isRemoved = true
            adapter.notifyItemChanged(pos)
            showSnackBar(pos)
        }

        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            return false
        }

    }

    fun showSnackBar(pos: Int) {
        var snackBar = Snackbar.make(toolbar, "User Deleted", Snackbar.LENGTH_LONG)

        snackBar.setAction("Undo", {
            adapter.userList.get(pos).isRemoved = false
            adapter.notifyItemChanged(pos)
            snackBar.dismiss()
        })
//        snackBar.setCallback(callBack)
        snackBar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivityForResult(Intent(this, UserAddActivity::class.java), requestCode)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && this.requestCode == requestCode) {
            adapter.userList.add(data!!.getParcelableExtra<User>("user"))
            adapter.notifyDataSetChanged()
        }
    }
}
