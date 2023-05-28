package com.example.armmanager.di

import android.app.Application
import androidx.room.Room
import com.example.armmanager.api.ArmService
import com.example.armmanager.database.ArmRoomDatabase
import com.example.armmanager.database.ProductDAO
import com.example.armmanager.database.RequestDAO
import com.example.armmanager.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideArmService(): ArmService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ArmService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): ArmRoomDatabase {
        return Room
            .databaseBuilder(app, ArmRoomDatabase::class.java, "arm.db")
            .fallbackToDestructiveMigration()
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideUserDao(db: ArmRoomDatabase): UserDao {
//        return db.userDao()
//    }

    @Singleton
    @Provides
    fun provideRequestDao(db: ArmRoomDatabase): RequestDAO {
        return db.requestDao()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: ArmRoomDatabase): ProductDAO {
        return db.productDao()
    }
}