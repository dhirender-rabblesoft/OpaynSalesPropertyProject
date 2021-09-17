package com.example.opaynpropertyproject.home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity(), View.OnClickListener,ApiResponse {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        search_bar_container.visibility = View.INVISIBLE
        ads.visibility = View.VISIBLE
        header_filer.visibility = View.INVISIBLE
        setUpNavigation()
        token=SharedPreferenceManager(this).getString(Keys.TOKEN).toString()
    }

    companion object{
        var token = ""
    }

    private fun setUpNavigation(){
        val navController = Navigation.findNavController(this,R.id.nav_container)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
        }
    }

    override fun onResponse(requestcode: Int, response: String) {

    }
}