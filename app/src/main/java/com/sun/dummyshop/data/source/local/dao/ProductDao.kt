package com.sun.dummyshop.data.source.local.dao

import androidx.room.*
import com.sun.dummyshop.data.model.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM product WHERE isFavorite = 1")
    fun getFavoriteProducts(): Observable<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product): Completable

    @Delete
    fun deleteFavoriteProduct(product: Product): Completable

    @Update
    fun updateProduct(product: Product): Completable

    @Query("SELECT * FROM product WHERE id = :id AND isFavorite = 1")
    fun getFavoriteProductById(id: String): Single<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id AND quantityInCart > 0")
    fun getProductAddedToCart(id: String): Single<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductById(id: String): Single<Product>

    @Query("SELECT * FROM product WHERE quantityInCart > 0")
    fun getAddedToCartProducts(): Single<List<Product>>

    @Update
    fun removeFromCart(product: Product): Single<Int>

    @Query("SELECT quantityInCart FROM product WHERE quantityInCart > 0")
    fun getItemInCartQuantity(): Single<List<Int>>
}
