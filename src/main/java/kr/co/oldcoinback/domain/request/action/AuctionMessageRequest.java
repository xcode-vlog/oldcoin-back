package kr.co.oldcoinback.domain.request.action;

import kr.co.oldcoinback.common.enums.AuctionMessageType;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionMessageRequest {
    private String message;
    private AuctionMessageType auctionMessageType;
}
