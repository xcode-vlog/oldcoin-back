package kr.co.oldcoinback.domain.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductInfoResponse {
    private Long productId;
    private String productTitle;
    private Long startPrice;
    private Long endPrice;
    private String imageUrl;
}
