package task.clone.shorts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import task.clone.shorts.netework.VideoPagingSource
import task.clone.shorts.netework.repository.VideoRepository
import javax.inject.Inject

@HiltViewModel
class ShortsViewModel @Inject constructor(private val videoRepository: VideoRepository) :
    ViewModel() {

        val usersPage = Pager(
            config = PagingConfig(
                pageSize = 9,
            )
        ){
            VideoPagingSource(videoRepository)
        }.flow.cachedIn(viewModelScope)

}