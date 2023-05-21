package task.clone.shorts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import task.clone.shorts.netework.ShortsApi
import task.clone.shorts.netework.VideoPagingSource
import task.clone.shorts.netework.repository.VideoRepository
import task.clone.shorts.netework.repository.VideoRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideShortsApi(): ShortsApi = ShortsApi()

    @Provides
    fun provideUserRepository(api: ShortsApi): VideoRepository = VideoRepositoryImpl(api)

}