package com.turtlecat.usermanager.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.koushikdutta.ion.Ion
import com.turtlecat.usermanager.R
import com.turtlecat.usermanager.bean.RandomUser
import com.turtlecat.usermanager.bean.User
import com.turtlecat.usermanager.utils.JavaUtils
import kotlinx.android.synthetic.main.activity_user_add.*

class UserAddActivity : AppCompatActivity() {
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add)
        setSupportActionBar(toolbar)
        title = ""
        fetchUser()
    }


    fun fetchUser() {
        Ion.with(this).load("https://randomuser.me/api/").`as`(RandomUser::class.java).setCallback { exception, randomUser ->
            if (exception == null) {
                user = randomUser.results.get(0)
                setData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        setResult(RESULT_CANCELED)
        finish()
        return true
    }

    override fun onBackPressed() {
        if (user != null) {
            var i = Intent()
            i.putExtra("user", user)
            setResult(RESULT_OK, i)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
    }

    fun setData() {
        userTitle.text = user?.name?.first + " " + user?.name?.last
        Ion.with(this).load(user?.picture?.large).asBitmap().setCallback {
            exception, bitmap ->
            var b = JavaUtils.fastBlur(bitmap, 10)
            userAvatarBackground.setImageBitmap(b)
            userAvatar.setImageBitmap(bitmap)
        }
    }
}
