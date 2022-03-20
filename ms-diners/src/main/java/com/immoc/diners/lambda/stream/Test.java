package com.immoc.diners.lambda.stream;


import com.immoc.diners.lambda.cart.CartService;
import com.immoc.diners.lambda.cart.Sku;
import com.immoc.diners.lambda.cart.SkuCategoryEnum;
import com.immoc.diners.lambda.cart.SkuTotalPricePredicate;

import java.util.List;

public class Test {
    public static void filterElectronicsSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(result.toString());
    }
    public static void filterSkusByCategory(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result  = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.CLOTHING);
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
        System.out.println(1111);
    }

    public static void xxx(){
        System.out.println(1111111111);
        System.out.println(2222);
    }


}
