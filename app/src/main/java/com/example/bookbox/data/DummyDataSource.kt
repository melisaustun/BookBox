package com.example.bookbox.data


import com.example.bookbox.R
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.model.books.BooksData
import io.reactivex.Observable

class DummyDataSource {

    fun getRental(): Observable<ArrayList<ProductEntity>> {


        val dummy1 = ProductEntity(
            name = "Kaplanın Sırtında", description = "Zülfü Livaneli",
            price = 54,
            picture = R.drawable.kaplaninsirtinda,
            id = 1
        )
        val dummy2 = ProductEntity(
            name = "Bir Ömür Nasıl Yaşanır", description = "İlber Ortaylı",
            price = 46,
            picture = R.drawable.biromurnasilyasanir,
            id = 2
        )
        val dummy3 = ProductEntity(
            name = "Camdaki Kız", description = "Gülseren Budayıcıoğlu",
            price = 90,
            picture = R.drawable.camdakikiz,
            id = 3
        )
        val dummy4 = ProductEntity(
            name = "İçimizdeki Şeytan", description = "Sabahattin Ali",
            price = 23,
            picture = R.drawable.icimizdekiseytan,
            id = 4
        )
        val dummy5 = ProductEntity(
            name = "Aşkımız Eski Bir Roman", description = "Ahmet Ümit",
            price = 32,
            picture = R.drawable.askimizeskibirroman,
            id = 5
        )
        val dummy6 = ProductEntity(
            name = "Kürk Mantolu Madonna", description = "Sabahattin Ali",
            price = 35,
            picture = R.drawable.kurkmantolumadonna,
            id = 6
        )
        val dummy7 = ProductEntity(
            name = "Hazan", description = "Ayşe Kulin",
            price = 41,
            picture = R.drawable.hazan,
            id = 7
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4, dummy5, dummy6, dummy7)
        return Observable.just(ArrayList(data))

    }

    fun getRentalAll(): Observable<ArrayList<ProductEntity>> {


        val dummy1 = ProductEntity(
            name = "Kaplanın Sırtında", description = "Zülfü Livaneli",
            price = 54,
            picture = R.drawable.kaplaninsirtinda,
            id = 8
        )
        val dummy2 = ProductEntity(
            name = "Bir Ömür Nasıl Yaşanır", description = "İlber Ortaylı",
            price = 46,
            picture = R.drawable.biromurnasilyasanir,
            id = 9
        )
        val dummy3 = ProductEntity(
            name = "Camdaki Kız", description = "Gülseren Budayıcıoğlu",
            price = 90,
            picture = R.drawable.camdakikiz,
            id = 10
        )
        val dummy4 = ProductEntity(
            name = "İçimizdeki Şeytan", description = "Sabahattin Ali",
            price = 23,
            picture = R.drawable.icimizdekiseytan,
            id = 11
        )
        val dummy5 = ProductEntity(
            name = "Aşkımız Eski Bir Roman", description = "Ahmet Ümit",
            price = 32,
            picture = R.drawable.askimizeskibirroman,
            id = 12
        )
        val dummy6 = ProductEntity(
            name = "Kürk Mantolu Madonna", description = "Sabahattin Ali",
            price = 35,
            picture = R.drawable.kurkmantolumadonna,
            id = 13
        )
        val dummy7 = ProductEntity(
            name = "Hazan", description = "Ayşe Kulin",
            price = 41,
            picture = R.drawable.hazan,
            id = 14
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4, dummy5, dummy6, dummy7)
        return Observable.just(ArrayList(data))

    }
    fun getBestSelling(): Observable<ArrayList<ProductEntity>> {

        val dummy1 = ProductEntity(
            name = "İnsanları Okumak", description = "Jo-Ellan Dimtirius",
            price = 48,
            picture = R.drawable.insanlariokumak,
            id = 15
        )
        val dummy2 = ProductEntity(
            name = "İrrasyonel", description = "Stuart Sutherland",
            price = 49,
            picture = R.drawable.irrasyonel,
            id = 16
        )
        val dummy3 = ProductEntity(
            name = "Yapay Zeka Devrimi", description = "Bernard Marr",
            price = 49,
            picture = R.drawable.yapayzekadevrimi,
            id = 17
        )
        val dummy4 = ProductEntity(
            name = "Sınırlar", description = "Dr.Henry Clodud",
            price = 36,
            picture = R.drawable.sinirlar,
            id = 18
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4)
        return Observable.just(ArrayList(data))
    }

    fun getBestSellingAll(): Observable<ArrayList<ProductEntity>> {

        val dummy1 = ProductEntity(
            name = "İnsanları Okumak", description = "Jo-Ellan Dimtirius",
            price = 48,
            picture = R.drawable.insanlariokumak,
            id = 19
        )
        val dummy2 = ProductEntity(
            name = "İrrasyonel", description = "Stuart Sutherland",
            price = 49,
            picture = R.drawable.irrasyonel,
            id = 20
        )
        val dummy3 = ProductEntity(
            name = "Yapay Zeka Devrimi", description = "Bernard Marr",
            price = 49,
            picture = R.drawable.yapayzekadevrimi,
            id = 21
        )
        val dummy4 = ProductEntity(
            name = "Sınırlar", description = "Dr.Henry Clodud",
            price = 36,
            picture = R.drawable.sinirlar,
            id = 22
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4)
        return Observable.just(ArrayList(data))
    }

    fun getCategory(): Observable<ArrayList<ProductEntity>> {

        val dummy1 = ProductEntity(
            name = "Sevgili Arsız Ölüm", description = "Latife Tekin",
            price = 51,
            picture = R.drawable.sevgiliarsizolum,
            id = 23
        )

        val dummy2 = ProductEntity(
            name = "Tarihin İzinde", description = "İlber Ortaylı",
            price = 49,
            picture = R.drawable.tarihinizinde,
            id = 24
        )

        val dummy3 = ProductEntity(
            name = "Bir Aşk Masalı", description = "Ahmet Ümit",
            price = 30,
            picture = R.drawable.biraskmasali,
            id = 25
        )

        val dummy4 = ProductEntity(
            name = "Mahfuz", description = "Eray Hacıosmanoğlu",
            price = 43,
            picture = R.drawable.mahfuz,
            id = 26
        )

        val dummy5 = ProductEntity(
            name = "Kar Tanesi", description = "Süleyman Bulut",
            price = 32,
            picture = R.drawable.kartanesi,
            id = 27
        )

        val dummy6 = ProductEntity(
            name = "Lanetli Tavşan", description = "Bora Chung",
            price = 44,
            picture = R.drawable.lanetlitavsan,
            id = 28
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4, dummy5, dummy6)
        return Observable.just(ArrayList(data))

    }

    fun getBook(): Observable<ArrayList<ProductEntity>> {

        val dummy1 = ProductEntity(
            name = "Sevgili Arsız Ölüm", description = "Latife Tekin",
            price = 51,
            picture = R.drawable.sevgiliarsizolum,
            id = 29
        )

        val dummy2 = ProductEntity(
            name = "Tarihin İzinde", description = "İlber Ortaylı",
            price = 49,
            picture = R.drawable.tarihinizinde,
            id = 30
        )

        val dummy3 = ProductEntity(
            name = "Bir Aşk Masalı", description = "Ahmet Ümit",
            price = 30,
            picture = R.drawable.biraskmasali,
            id = 31
        )

        val dummy4 = ProductEntity(
            name = "Mahfuz", description = "Eray Hacıosmanoğlu",
            price = 43,
            picture = R.drawable.mahfuz,
            id = 32
        )

        val dummy5 = ProductEntity(
            name = "Kar Tanesi", description = "Süleyman Bulut",
            price = 32,
            picture = R.drawable.kartanesi,
            id = 33
        )

        val dummy6 = ProductEntity(
            name = "Lanetli Tavşan", description = "Bora Chung",
            price = 44,
            picture = R.drawable.lanetlitavsan,
            id = 34
        )

        val data = listOf(dummy1, dummy2, dummy3, dummy4, dummy5, dummy6)
        return Observable.just(ArrayList(data))
    }

    fun getSearchData(qword: String?): Observable<List<ProductEntity>> {
        val listData = listOf(getBestSelling(), getRental(), getCategory())
        val resultData = mutableListOf<ProductEntity>()

        return Observable.merge(listData).doOnNext{
            resultData.addAll(it)
        }
            .map {
                return@map resultData.filter {
                    it.name.contains(qword.orEmpty(), true)
                }
            }
    }

}