package task.clone.shorts.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import task.clone.shorts.exoplayer.MetaDataReader
import task.clone.shorts.exoplayer.MetaDataReaderImpl
import task.clone.shorts.netework.ShortsApi
import task.clone.shorts.netework.VideoPagingSource
import task.clone.shorts.netework.repository.VideoRepository
import task.clone.shorts.netework.repository.VideoRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideShortsApi(): ShortsApi = ShortsApi()

    @Provides
    fun provideUserRepository(api: ShortsApi): VideoRepository = VideoRepositoryImpl(api)

    @Provides
    @ViewModelScoped
    fun provideVideoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app)
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideMetaDataReader(app: Application): MetaDataReader {
        return MetaDataReaderImpl(app)
    }

}