package com.airbag.dis.disfoot.model

import androidx.room.*

@Entity(tableName = "selected_shoe")

class SelectedShoe(@ColumnInfo(name = "selected_shoe") val selectedShoe : Shoe, @ColumnInfo(name = "scan_name") val  scanName : String,@ColumnInfo(name = "scan_time") val  scanTime:String,@ColumnInfo(name = "size") val size:String,@ColumnInfo(name = "fit") val fit:String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun toString(): String {
        return "SelectedShoe(selectedShoe=$selectedShoe, scanName='$scanName', scanTime='$scanTime', size='$size', fit='$fit', id=$id)"
    }
}