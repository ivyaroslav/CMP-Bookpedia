package com.plcoding.bookpedia.book.presentation.book_list.bookDetail.components

import com.plcoding.bookpedia.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick:BookDetailAction
    data object OnFavoriteClick:BookDetailAction
    data class OnSelectedBookChanged(val book: Book):BookDetailAction
}