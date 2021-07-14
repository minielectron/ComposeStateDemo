package com.apd.composestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenVM: ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _showOutput: MutableLiveData<Boolean> = MutableLiveData(false)
    val showOutput : LiveData<Boolean> = _showOutput

    fun onNameChange(name: String){
        _name.value = name
    }

    fun enableShowOutput(enable: Boolean){
        _showOutput.value = enable
    }

}