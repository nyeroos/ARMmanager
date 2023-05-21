package com.example.armmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.armmanager.vo.Product
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Request class
@Database(
    entities = [Request::class,
                User::class,
                Product::class],
    version = 1,
    exportSchema = false)
public abstract class ArmRoomDatabase : RoomDatabase() {

    abstract fun requestDao(): RequestDAO

    private class RequestDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.requestDao())
                }
            }
        }

        suspend fun populateDatabase(requestDAO: RequestDAO) {
            // Delete all content here.
            requestDAO.deleteAll()

            // Add sample words.
            var request = Request(1, 23003, "Pfzdrf1", "punk", "23.05.2023", "19.05.2023", "22.05.2023", 2, "В работе")
            requestDAO.insert(request)
            request = Request(2, 23005, "Zizn", "Solominka", "24.05.2023","19.05.2023", "22.05.2023", 3, "В работе" )
            requestDAO.insert(request)

            // TODO: Add your own words!
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: ArmRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): ArmRoomDatabase {
            // if the INSTANCE is not null, then return it, if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArmRoomDatabase::class.java,
                    "arm_database"
                ).addCallback(RequestDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}