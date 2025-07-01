package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.map
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class BookListViewModel(
    private val bookRepository: BookRepository
): ViewModel() {
    private var cachedBooks = emptyList<Book>()
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.OnBookClick -> {

            }
            is BookListAction.OnTabSelected -> {

            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(
                            errorMessage = null,
                            searchResults = cachedBooks
                        ) }
                    }
                    query.length >= 2 -> {
                        searchBooks(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) {
        _state.update { it.copy(
            isLoading = true,

        )}
            viewModelScope.launch {
                bookRepository
                    .searchBooks(query)
                    .onSuccess { searchResults ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = null,
                                searchResults = searchResults
                            )
                        }
                    }
                    .onError { error ->
                        _state.update {
                            it.copy(
                                searchResults = emptyList(),
                            )
                        }
                    }
            }
    }
}