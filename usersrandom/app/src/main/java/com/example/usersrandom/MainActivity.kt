package com.example.usersrandom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.usersrandom.userRandom.UserRandomFragment
import com.example.usersrandom.viewModel.VMUsersRandom

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewModelProvider(this)[VMUsersRandom::class.java]
        goToFragment(UserRandomFragment())
    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.clContainer, fragment)
            .addToBackStack(null).commit()
    }
}