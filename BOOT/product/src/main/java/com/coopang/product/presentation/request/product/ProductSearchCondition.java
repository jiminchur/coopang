package com.coopang.product.presentation.request.product;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSearchCondition extends BaseSearchCondition {

    private String productName;
    private double minProductPrice;
    private double maxProductPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
