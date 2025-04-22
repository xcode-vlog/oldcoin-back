package kr.co.oldcoinback.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDomain {
    private Long productId;
    private String productTitle;
    private Long startPrice;
    private Long endPrice;
    private String imageUrl;
}
