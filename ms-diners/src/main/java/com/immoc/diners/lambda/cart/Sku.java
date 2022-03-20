package com.immoc.diners.lambda.cart;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sku {
    private Integer skuId;
    private String skuName;
    private Double skuPrice;
    private Integer totalNum;
    private Double totalPrice;
    private Enum skuCatrgoty;

}
