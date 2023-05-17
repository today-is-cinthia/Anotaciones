package com.astro.mvvm_notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astro.mvvm_notes.data.db.repository.AnotacionRepository

//obtiene las dependencias necesarias para crear ViewModel

class AnotacionViewModelFactory(
    private val repository: AnotacionRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnotacionViewModel(repository) as T
    }

}