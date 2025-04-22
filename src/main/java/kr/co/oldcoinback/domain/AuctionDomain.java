package kr.co.oldcoinback.domain;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionDomain {
    private Long auctionId;
    private String auctionKey;
    private Long productId;
    private AuctionStatus status;
    private GetProductInfoResponse productInfo;
    private String isStart;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
