package com.airbag.dis.disfoot.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.airbag.dis.disfoot.model.OtherShoe

@Dao
interface DaoOtherShoes {

    @Query("SELECT * from OTHER_SHOES WHERE selected_shoe_external_id = :externalShoeId")
    fun getAllOtherShoesForShoeScan(externalShoeId: Long): List<OtherShoe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(othetShoes: List<OtherShoe>):Array<Long>


    @Query("DELETE FROM OTHER_SHOES WHERE selected_shoe_external_id = :externalShoeId")
    suspend fun deleteAll(externalShoeId: Long)
}