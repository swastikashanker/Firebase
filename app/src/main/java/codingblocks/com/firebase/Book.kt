package codingblocks.com.firebase

class Book (val uid:String,
    val title:String,
            val author:String,
            val buyPrice:String,
            val rentPrice:String){

    constructor():this("","","","",""){


    }
}