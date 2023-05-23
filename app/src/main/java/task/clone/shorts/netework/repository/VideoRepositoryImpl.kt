package task.clone.shorts.netework.repository

import kotlinx.coroutines.delay
import task.clone.shorts.netework.ShortsApi
import task.clone.shorts.netework.dataClass.Video

class VideoRepositoryImpl(private val api: ShortsApi): VideoRepository {
    override suspend fun getShorts(page: Int): Video {
        delay(1000L)
        return  api.getVideos(page)
    }
}