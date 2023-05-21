package task.clone.shorts.netework.repository

import task.clone.shorts.netework.ShortsApi
import task.clone.shorts.netework.dataClass.Video

class VideoRepositoryImpl(private val api: ShortsApi): VideoRepository {
    override suspend fun getShorts(page: Int): Video {
        return  api.getVideos(page)
    }
}