package com.plcoding.bookpedia.book.presentation.book_list.bookDetail.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.domain.BookRepository
import kotlinx.coroutines.launch
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.*

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel(
) {
    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart { fetchBookDescription() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)
    private val bookId = savedStateHandle.toRoute<Route.BookDetail>().id

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

    private fun fetchBookDescription() {
        viewModelScope.launch {
            val bookId = savedStateHandle.toRoute<Route.BookDetail>().id
            bookRepository
                .getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update {
                    it.copy(
                        book = it.book?.copy(
                            description = description
                        ),
                        isLoading = false,
                    )
                } }
        }
    }
}