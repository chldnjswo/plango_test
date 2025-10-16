package com.example.plango_nickname.name

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.connection.Exchange

class NameViewModel : ViewModel(){
    private val repository = NameRepository
    private val _displayName = MutableStateFlow("이름을 입력하세요")
    val displayName : StateFlow<String> = _displayName.asStateFlow()

    fun sendName(name :String){
        _displayName.value ="이름을 보내는 중입니다"
        viewModelScope.launch{
            try{
                val response=repository.sendName(name)
                if(response.success){
                    _displayName.value="메세지 : ${response.message}"
                }
                else{
                    _displayName.value="에러 : ${response.message}"
                }
            }catch (e : Exception){
                e.printStackTrace()


                Log.e("NameViewModel", "서버 요청 중 예외 발생", e)
                _displayName.value = "서버 연결 실패 (${e.javaClass.simpleName})"

            }
        }
    }

}