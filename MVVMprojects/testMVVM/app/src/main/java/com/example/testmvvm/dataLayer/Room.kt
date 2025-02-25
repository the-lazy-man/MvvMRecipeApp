package com.example.testmvvm.dataLayer

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String?,
    val email : String?
)

@Dao
interface Userao{

        @Query("insert into users('name','email')values('1','2')")
        suspend fun insertUser( user : User):Long

        @Query("Select * from users")
        fun getAllUsers() : LiveData<List<User>>
}


@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun Userao(): Userao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
//package com.example.testmvvm.dataLayer
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.room.*

//@Entity(tableName = "users")
//data class User(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val name: String?,
//    val email: String?
//)
//
//@Dao
//interface UserDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: User) // ✅ Correct insert method
//
//    @Query("SELECT * FROM users")
//    fun getAllUsers(): LiveData<List<User>>
//}
//
//@Database(entities = [User::class], version = 1)
//abstract class MyDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: MyDatabase? = null
//
//        fun getInstance(context: Context): MyDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MyDatabase::class.java,
//                    "my_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
