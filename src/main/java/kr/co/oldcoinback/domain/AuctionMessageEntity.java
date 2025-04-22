package kr.co.oldcoinback.domain;

import kr.co.oldcoinback.common.enums.AuctionMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionMessageEntity {
    private Long messageId;
    private String auctionKey;
    private String senderId;
    private Long auctionPrice;
    private AuctionMessageType auctionMessageType;
    private Integer priority;
    private String award;
}
