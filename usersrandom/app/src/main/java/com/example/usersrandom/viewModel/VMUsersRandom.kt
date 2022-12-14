package com.example.usersrandom.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersrandom.network.InterfazUsers
import com.example.usersrandom.network.RetrofitConect
import com.example.usersrandom.network.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VMUsersRandom : ViewModel() {
    var users:MutableLiveData<Users> = MutableLiveData()

    init {
        getUserRandom()
    }

    fun getUserRandom() {
        val retrofitConect =
            RetrofitConect.getInstanceRetrofit().create(InterfazUsers::class.java)
        retrofitConect.getUser().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.body() != null)
                    users.postValue(response.body())
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                getUserRandom()
            }

        })
    }
}