package com.reactions.deathlines.data

import com.com.reactions.deathlines.data.api.SongApi

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseNetworkTest {

    lateinit var songApi: SongApi

    val amountFake = "123"
    val emailFake = "jorge@quebuu.com"

    @Before
    open fun setUp() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.apple.com/itunes/")
                .build()
        songApi = retrofit.create(SongApi::class.java)
    }

}
