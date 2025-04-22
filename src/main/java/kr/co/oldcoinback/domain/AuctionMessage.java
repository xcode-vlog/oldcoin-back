package kr.co.oldcoinback.domain;

import kr.co.oldcoinback.common.enums.AuctionMessageType;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionMessage {
    private String senderId;
    private String senderName;
    private String auctionKey;
    private AuctionMessageType auctionMessageType;
    private Long auctionPrice;
    private String award;
    private List<GetAuctionMember> memberList;
    private Long messageId;
}
