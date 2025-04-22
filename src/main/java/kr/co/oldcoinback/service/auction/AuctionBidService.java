package kr.co.oldcoinback.service.auction;

import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.AuctionMessageEntity;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;

import java.util.List;

public interface AuctionBidService {
    boolean processMaxBid(AuctionMessageEntity auctionMessageEntity);
    boolean processNormallyBid(AuctionMessageEntity auctionMessageEntity);

    List<GetAuctionMember> enterAuction(String auctionKey, String userId);
    List<GetAuctionMember> leaveAuction(String auctionKey, String userId);

    List<AuctionMessage> updateAwardAuctionMember(String auctionKey);

}
