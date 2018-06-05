package com.classes;

class SellSystem
{
    private int sellProductPrice;

    SellSystem(int sellProductPrice)
    {
        this.sellProductPrice = sellProductPrice;
    }

    boolean IsEnoughToSell(int actualProductPrice)
    {
        return actualProductPrice>=sellProductPrice;
    }

    int GetSellProductPrice()
    {
        return sellProductPrice;
    }
}
