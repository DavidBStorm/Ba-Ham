package com.task_baham.di.module

import android.content.Context
import com.task_baham.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MyApplicationModule {

    @Provides
    fun providesMainApplicationInstance(@ApplicationContext context: Context): MyApplication {
        return context as MyApplication
    }
}