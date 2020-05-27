package com.airbag.dis.disfoot.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "other_shoes")
class OtherShoe(@ColumnInfo(name = "selected_shoe_external_id") val valselectedShoeId : Long,val shoe: Shoe,val category : String,val size:String,val fit:String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun toString(): String {
        return "OtherShoe(valselectedShoeId=$valselectedShoeId, shoe=$shoe, category='$category', size='$size', fit='$fit', id=$id)"
    }
}