package com.wangxingxing.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.flowpractice.db.AppDatabase
import com.wangxingxing.flowpractice.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2021/7/21 19:29
 * email : 1099420259@qq.com
 * description :
 */
class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val TAG = "UserViewModel"

    fun getAll(): Flow<List<User>> {
        return AppDatabase.getInstance(getApplication())
            .userDao()
            .getAll()
            .catch { e ->
                e.printStackTrace()
                Log.e(TAG,  e.message ?: "exception caught")
            }.flowOn(Dispatchers.IO)
    }
    
    fun insert(uid: String, firstName: String, lastName: String) {
        viewModelScope.launch(Dispatchers.IO) { 
            AppDatabase.getInstance(getApplication()).userDao()
                .insert(User(uid.toInt(), firstName, lastName))
            Log.d(TAG, "insert: user:$uid")
        }
    }
}