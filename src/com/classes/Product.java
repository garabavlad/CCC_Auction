package com.classes;

class Product
{
    private int productPrice;

    Product(int initialProductPrice)
    {
        this.productPrice = initialProductPrice;
    }

    void SetProductPrice(int newPrice)
    {
        this.productPrice = newPrice;
    }

    int GetProductPrice()
    {
        return productPrice;
    }
}