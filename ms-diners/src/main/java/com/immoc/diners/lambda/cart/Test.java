package com.immoc.diners.lambda.cart;


import java.util.List;

public class Test {
    public static void filterElectronicsSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(result.toString());
    }
    public static void filterSkusByCategory(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterSkusByCategory(cartSkuList,SkuCategoryEnum.CLOTHING);
        System.out.println(result.toString());
    }
    public static void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterSkus(cartSkuList,new SkuTotalPricePredicate());
        System.out.println(result.toString());
    }
    public static void filterSkus2(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterSkus(cartSkuList,(Sku sku)->sku.getTotalPrice()>1000);
        System.out.println(result.toString());
    }
    public static void main(String[] args) {
//        filterElectronicsSkus();
        filterSkus2();
    }
}