package com.immoc.diners.lambda.stream;

import com.google.common.util.concurrent.AtomicDouble;
import com.immoc.diners.lambda.cart.CartService;
import com.immoc.diners.lambda.cart.Sku;
import com.immoc.diners.lambda.cart.SkuCategoryEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StreamVs {
    public static void oldCartHandle(){
        List<Sku> skuList = CartService.getCartSkuList();
        for (Sku sku:skuList) {
            System.out.println(sku.toString());
        }

        List<Sku> notBooksSkuList = new ArrayList<Sku>();
        for (Sku sku:skuList){
            if(!SkuCategoryEnum.BOOKS.equals(sku.getSkuCatrgoty())){
                notBooksSkuList.add(sku);
            }
        }

        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                if(o1.getTotalPrice()>o2.getTotalPrice()){
                    return  -1;
                }else if(o1.getTotalPrice()<o2.getTotalPrice()) {
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        List<Sku> top2SkuList = new ArrayList<Sku>();
        for(int i = 0;i<2;i++){
            top2SkuList.add(notBooksSkuList.get(i));
        }
        Double money = 0.0;
        for (Sku sku:top2SkuList){
            money+=sku.getTotalPrice();
        }
        List<String> ressultSkuNameList = new ArrayList<String>();
        for (Sku sku:top2SkuList)
        {
            ressultSkuNameList.add(sku.getSkuName());
        }
        System.out.println(ressultSkuNameList.toString());
        System.out.println(money);
    }
    public static void newCartHandle(){
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));
        List<String> list = CartService.getCartSkuList().stream().peek(sku -> System.out.println(sku.toString()))
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCatrgoty()))
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .limit(2)
                .peek(sku -> money.set(money.get()+sku.getTotalPrice()))
                .map(sku -> sku.getSkuName())
                .collect(Collectors.toList());
        System.out.println(list.toString());
        System.out.println(money);
    }

    public static void main(String[] args) {
        newCartHandle();
    }
}
