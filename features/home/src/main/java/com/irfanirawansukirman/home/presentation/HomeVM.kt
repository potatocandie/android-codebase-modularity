package com.irfanirawansukirman.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import kotlinx.coroutines.launch

interface HomeContract {
    fun getLanguage()

    fun setupImagePath(imagePath: String)
}

class HomeVM(
    private val languageUseCase: LanguageUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseVM(), HomeContract {

    private val _imagePath = MutableLiveData<String>()
    val imagePath: LiveData<String>
        get() = _imagePath

    private val _languageState = MutableLiveData<ViewState<LanguangeMapper>>()
    val languageState: LiveData<ViewState<LanguangeMapper>>
        get() = _languageState

    override fun setupImagePath(imagePath: String) {
        this._imagePath.value = imagePath
    }

    override fun getLanguage() {
        _languageState.value = ViewState.loading()

        viewModelScope.launch(coroutineContextProvider.main) {
            languageUseCase()
                .onSuccess { _languageState.value = ViewState.success(it) }
                .onFailure { _languageState.value = ViewState.error(it.throwable) }
        }
    }

}