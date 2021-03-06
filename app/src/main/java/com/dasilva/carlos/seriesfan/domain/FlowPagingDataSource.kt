package com.dasilva.carlos.seriesfan.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dasilva.carlos.seriesfan.network.data.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

private const val FIRST_PAGE = 0
private const val NOT_FOUND_CODE = 404

class FlowPagingDataSource<T : Any, R : Any> (
    private val converter: (List<T>) -> List<R>,
    private val flowProvider: (Int) -> Flow<List<T>>
) : PagingSource<Int, R>() {

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.inc()
                ?: state.closestPageToPosition(it)?.nextKey?.dec()
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        val key = params.key ?: FIRST_PAGE
        val prevKey = if (key > FIRST_PAGE) key - 1 else null

        return flowProvider(key)
            .map<List<T>, LoadResult<Int, R>> {
                return@map LoadResult.Page(
                    data = converter(it),
                    prevKey = prevKey,
                    nextKey = key.inc()
                )
            }
            .catch { e ->
                if (e is ResponseException && e.code == NOT_FOUND_CODE) {
                    emit(
                        LoadResult.Page(
                            data = listOf(),
                            prevKey = prevKey,
                            nextKey = null
                        )
                    )
                } else {
                    emit(LoadResult.Error(e))
                }
            }.single()
    }
}
