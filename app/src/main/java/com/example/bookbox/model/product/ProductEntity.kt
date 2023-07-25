package com.example.bookbox.model.product

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookbox.utill.ProductSavedType
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product") //Veri tabanındaki tablo ismini belirtir
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) // Her nesnenin birbirinden farklı id oluşturmasını sağlar
    @ColumnInfo(name="pk") //Sütunlar bu etiket ile oluşur
    val pk: Int = 0,
    @ColumnInfo(name="id")
    val id: Int = 0,
    @ColumnInfo(name= "name")
    val name: String = "",
    @ColumnInfo(name= "picture")
    val picture: Int = 0,
    @ColumnInfo(name= "description")
    val description: String = "",
    @ColumnInfo(name= "price") //fiyat
    val price: Int = 0,
    @ColumnInfo(name= "qty") //adet
    val qty: Int = 0,
    @ColumnInfo(name = "type")
    val type: Int = ProductSavedType.FAV

): Parcelable{ // sayfalar arası daha hızlı veri transferi

    val priceToQty get() = qty * price
}
