package task.clone.shorts.netework


import androidx.paging.PagingSource
import androidx.paging.PagingState
import task.clone.shorts.netework.dataClass.Post
import task.clone.shorts.netework.repository.VideoRepository

class VideoPagingSource(private val repo: VideoRepository): PagingSource<Int, Post>(){
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
       return state.anchorPosition?.let {position ->
           val page = state.closestPageToPosition(position)
           page?.prevKey?.minus(1)?: page?.nextKey?.plus(1)

       }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPage = params.key ?: 1
            val response = repo.getShorts(nextPage)
            LoadResult.Page(
                data = response.data.posts,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.data.posts.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}