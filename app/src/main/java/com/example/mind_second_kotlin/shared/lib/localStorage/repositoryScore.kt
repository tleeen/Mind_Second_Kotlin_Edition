package com.example.mind_second_kotlin.shared.lib.localStorage

import android.content.Context
import android.content.SharedPreferences
import com.example.mind_second_kotlin.shared.lib._interface.IRepositoryScore

object RepositoryScore : IRepositoryScore{
  private lateinit var preference: SharedPreferences

  fun initialize(context: Context) {
    preference = context.getSharedPreferences("LS", Context.MODE_PRIVATE)
  }

  override suspend fun setScore(value: Int) {
    val editor = preference.edit()
    editor.putInt("bestScore", value)
    editor.apply()
  }

  override suspend fun getScore(): Int {
    return preference.getInt("bestScore", 0)
  }
}