package com.immoc.diners.lambda.stream;

import com.immoc.diners.lambda.cart.CartService;
import com.immoc.diners.lambda.cart.Sku;
import com.immoc.diners.lambda.cart.SkuCategoryEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StreamOpt {
    public static void filterTest(){
        CartService.getCartSkuList().stream()
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCatrgoty()))
                .forEach(item-> System.out.println(item.toString()));
    }
    public static void mapTest(){
        CartService.getCartSkuList().stream()
        .map(sku -> sku.getSkuName())
                .forEach(item-> System.out.println(item.toString()));
    }

    public static void flatMapTest(){
        CartService.getCartSkuList().stream()
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item-> System.out.println(item.toString()));
    }
    public static void peekTest(){
        CartService.getCartSkuList().stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item-> System.out.println(item.toString()));
    }
    public static void sortedTest(){
        CartService.getCartSkuList().stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(item-> System.out.println(item.toString()));

    }
    public static void distinctTest(){
        CartService.getCartSkuList().stream()
        .map(sku -> sku.getSkuCatrgoty())
                .distinct()
                .forEach(item-> System.out.println(item.toString()));
    }
    public  static void skipTest(){
        CartService.getCartSkuList().stream()
.sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .forEach(item-> System.out.println(item.toString()));

    }
    public  static void limitTest(){
        CartService.getCartSkuList().stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .limit(3)
                .forEach(item-> System.out.println(item.toString()));

    }


    public static void allMatchTest(){
       boolean match =  CartService.getCartSkuList().stream()
               .peek(sku -> System.out.println(sku.getSkuName()))
.allMatch(sku -> sku.getTotalPrice()>100);
        System.out.println(match);

    }

    public static void anyMatchTest(){
        boolean match =  CartService.getCartSkuList().stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .anyMatch(sku -> sku.getTotalPrice()>100);
        System.out.println(match);

    }
    public static void noneMatchTest(){
        boolean match =  CartService.getCartSkuList().stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .noneMatch(sku -> sku.getTotalPrice()>100);
        System.out.println(match);
    }
    public static void maxTest(){
        OptionalDouble optionalDouble = CartService.getCartSkuList().stream().mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }
    public static void minTest(){
        OptionalDouble optionalDouble = CartService.getCartSkuList().stream().mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }
    public static void countTest(){
        Long count = CartService.getCartSkuList().stream().count();
        System.out.println(count);
    }
    public static void main(String[] args) {
        maxTest();
        minTest();
    }
}
