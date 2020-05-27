package com.airbag.dis.disfoot.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.airbag.dis.disfoot.model.SelectedShoe

@Dao
interface DaoSelectedShoe {

    @Query("SELECT * from SELECTED_SHOE ORDER BY id ASC")
    fun getAllSelectedShoes(): LiveData<List<SelectedShoe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selectedShoe: SelectedShoe):Long

    @Delete
    suspend fun delete(shoe : SelectedShoe)

    @Query("DELETE FROM SELECTED_SHOE")
    suspend fun deleteAll()


}
