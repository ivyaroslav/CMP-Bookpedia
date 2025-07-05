package com.plcoding.bookpedia.book.presentation.book_list.bookDetail.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailViewModel: ViewModel() {
    private val _state = MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: BookDetailAction) {
        when(action) {
            is BookDetailAction.OnSelectedBookChanged -> {
                _state.update { it.copy(
                    book = action.book
                ) }
            }
            is BookDetailAction.OnFavoriteClick -> {

            }
            else -> Unit
        }
    }
}