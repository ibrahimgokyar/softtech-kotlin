package com.opendart.mvvmlitedataornek.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.opendart.mvvmlitedataornek.mainviewmodel.MainViewModel
import com.opendart.mvvmlitedataornek.repository.MainRepository

import java.lang.IllegalArgumentException

class MyViewModelFactory  constructor(private val repository: MainRepository) : ViewModelProvider.Factory{
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository)as T
        }else {
            throw IllegalArgumentException("ViewModel bulunamadı")
        }
    }
}