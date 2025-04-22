package kr.co.oldcoinback.domain.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {
    private String productTitle;
    private Long startPrice;
    private Long endPrice;
    private String imageUrl;
}
