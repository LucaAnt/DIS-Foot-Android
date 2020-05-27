package com.airbag.dis.disfoot.room
import android.content.Context
import androidx.room.*
import com.airbag.dis.disfoot.model.OtherShoe
import com.airbag.dis.disfoot.model.SelectedShoe
import com.airbag.dis.disfoot.room.converter.Converters
import com.airbag.dis.disfoot.room.dao.DaoOtherShoes
import com.airbag.dis.disfoot.room.dao.DaoSelectedShoe

@Database(entities = arrayOf(SelectedShoe::class,OtherShoe::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
    abstract class DisRoomDatabase : RoomDatabase() {

        abstract fun daoSelectedShoe(): DaoSelectedShoe
        abstract fun daoOtherShoes(): DaoOtherShoes

        companion object {
            @Volatile
            private var INSTANCE: DisRoomDatabase? = null

            fun getDatabase(context: Context): DisRoomDatabase {
                val tempInstance =
                    INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DisRoomDatabase::class.java,
                        "dis_room_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
