package com.jcasben.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jcasben.rickmortyapp.data.remote.ApiService
import com.jcasben.rickmortyapp.domain.model.EpisodeModel
import io.ktor.utils.io.errors.IOException

class EpisodesPagingSource(private val apiService: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllEpisodes(page)
            val episodes = response.results

            val prev = if (response.info.prev != null) page - 1 else null
            val next = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = episodes.map { it.toDomain() },
                prevKey = prev,
                nextKey = next
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}