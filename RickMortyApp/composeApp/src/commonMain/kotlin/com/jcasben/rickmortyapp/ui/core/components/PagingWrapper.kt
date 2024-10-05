package com.jcasben.rickmortyapp.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems

sealed class PagingType {
    data class Row(
        val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
    ) : PagingType()

    data class Column(
        val verticalArrangement: Arrangement.Vertical = Arrangement.Top
    ) : PagingType()

    data class HorizontalGrid(
        val cells: Int,
        val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
        val verticalArrangement: Arrangement.Vertical = Arrangement.Top
    ) : PagingType()

    data class VerticalGrid(
        val cells: Int,
        val horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
        val verticalArrangement: Arrangement.Vertical = Arrangement.Top
    ) : PagingType()
}

@Composable
fun <T : Any> PagingWrapper(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<T>,
    pagingType: PagingType,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    header: @Composable () -> Unit = {},
    itemView: @Composable (T) -> Unit
) {
    when {
        // Initial loading
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            initialView()
        }

        // No elements
        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyView()
        }

        else -> {
            when (pagingType) {
                is PagingType.Column -> {
                    LazyColumn(
                        modifier = modifier,
                        verticalArrangement = pagingType.verticalArrangement
                    ) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                    }
                }

                is PagingType.HorizontalGrid -> {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(pagingType.cells),
                        modifier = modifier,
                        verticalArrangement = pagingType.verticalArrangement,
                        horizontalArrangement = pagingType.horizontalArrangement
                    ) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                    }
                }

                is PagingType.Row -> {
                    LazyRow(
                        modifier = modifier,
                        horizontalArrangement = pagingType.horizontalArrangement
                    ) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                    }
                }

                is PagingType.VerticalGrid -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(pagingType.cells),
                        modifier = modifier,
                        verticalArrangement = pagingType.verticalArrangement,
                        horizontalArrangement = pagingType.horizontalArrangement,
                    ) {
                        item(span = { GridItemSpan(2) }) { header() }
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                        item { Spacer(Modifier.size(8.dp)) }
                    }
                }
            }

            // Appending new elements
            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }
        }
    }
}