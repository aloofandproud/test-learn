package com.immoc.diners.lambda.cart;

public class SkuTotalPricePredicate implements SkuPredicate{
    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice()>2000;
    }
}
