package com.example.moedas.di

import com.example.moedas.data.api.AwesomeAPI
import com.example.moedas.data.repository.CoinsRepository
import com.example.moedas.data.repository.ICoinsRepository
import com.example.moedas.domain.usecase.MoedasUseCase
import com.example.moedas.utils.Constantes
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    @Provides
    fun provideAwesomeApi(retrofit: Retrofit) : AwesomeAPI {
        return retrofit.create(AwesomeAPI::class.java)
    }

    @Provides
    fun provideRepositoryCoins(awesomeAPI: AwesomeAPI) : ICoinsRepository {
        return CoinsRepository( awesomeAPI )
    }

    @Provides
    fun provideMoedasUseCase(repository: ICoinsRepository) : MoedasUseCase{
        return MoedasUseCase(repository)
    }

}