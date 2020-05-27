package com.airbag.dis.disfoot.model

data class MeasureRequest(val idModel : String, val idScan : String, val email : String, val yourName : String, val gender : String, val scanName : String, val rightMeasure : ShoeSizeMeasure, val leftMeasure : ShoeSizeMeasure)
{
    companion object{
        //TODO: Debug Code
        fun getDummyRequest():MeasureRequest
        {
            ////{"gender":"M","idScan":"6ON95S","yourName":"no_name",
            // "rightMeasure":{"ballGirth":229.92500000000001,"instepGirth":242.41800000000001,"footLength":253.89099999999999},
            // "idModel":"65","email":"asd@asd.it","scanName":"",
            // "leftMeasure":{"ballGirth":230.31700000000001,"instepGirth":238.309,"footLength":255.13300000000001}}
            return MeasureRequest("14","test","test@airbag.it","Luca","M","Prova",
                ShoeSizeMeasure.getDummyScanRight(),
                ShoeSizeMeasure.getDummyScanRight()
            )
        }
    }
}
data class ShoeSizeMeasure(val footLength:Double,val ballGirth:Double,val instepGirth:Double){
    companion object{
        //TODO: Debug Code
        fun getDummyScanRight():ShoeSizeMeasure
        {
            return ShoeSizeMeasure(253.89099999999999, 229.92500000000001, 242.41800000000001)

        }

        fun getDummyScanLeft():ShoeSizeMeasure
        {
            return ShoeSizeMeasure(255.13300000000001, 230.31700000000001, 238.309)

        }
    }
}
data class ShoeSizeResponse(val size:String,val fittingLevel:String)