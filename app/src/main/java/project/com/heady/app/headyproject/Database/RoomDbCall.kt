package project.com.heady.app.headyproject.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import project.com.heady.app.headyproject.Models.FinalProduct
import project.com.heady.app.headyproject.Models.FinalVariant

@Database(entities = [FinalProduct::class, FinalVariant::class], version = 2,exportSchema = false)
public abstract class RoomDbCall : RoomDatabase() {

    abstract fun daoAccess(): DaoAccess

    companion object {
        @Volatile
        private var INSTANCE: RoomDbCall? = null

        fun getProducts(context: Context): RoomDbCall {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDbCall::class.java,
                    "product"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
        fun getVariants(context: Context): RoomDbCall {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDbCall::class.java,
                    "variant"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
