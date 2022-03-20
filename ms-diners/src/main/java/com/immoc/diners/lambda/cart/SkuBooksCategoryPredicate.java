package com.immoc.diners.lambda.cart;

public class SkuBooksCategoryPredicate implements SkuPredicate{
    @Override
    public boolean test(Sku sku) {
        return SkuCategoryEnum.BOOKS.equals(sku.getSkuCatrgoty());
    }
}
