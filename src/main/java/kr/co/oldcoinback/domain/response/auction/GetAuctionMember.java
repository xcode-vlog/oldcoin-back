package kr.co.oldcoinback.domain.response.auction;

import lombok.Getter;

@Getter
public class GetAuctionMember {
    private String auctionKey;
    private String userId;
    private String nickName;
}
