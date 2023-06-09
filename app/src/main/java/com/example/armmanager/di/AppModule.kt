package com.example.armmanager.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.armmanager.api.ArmService
import com.example.armmanager.api.HeaderInterceptor
import com.example.armmanager.database.ArmRoomDatabase
import com.example.armmanager.database.ProductDAO
import com.example.armmanager.database.RequestDAO
import com.example.armmanager.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(sharedPreferences: SharedPreferences?): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(HeaderInterceptor(sharedPreferences)).addInterceptor(logging).build()
    }

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences? {
        return application.getSharedPreferences("login", Context.MODE_PRIVATE)
    }
    @Singleton
    @Provides
    fun provideArmService(client: OkHttpClient): ArmService {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

            .client(client)
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