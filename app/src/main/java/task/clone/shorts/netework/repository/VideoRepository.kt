package task.clone.shorts.netework.repository

import task.clone.shorts.netework.dataClass.Video

interface VideoRepository {
    suspend fun getShorts(page: Int): Video
}