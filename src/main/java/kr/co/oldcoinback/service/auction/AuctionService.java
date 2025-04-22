package kr.co.oldcoinback.service.auction;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.domain.AuctionDomain;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.request.action.CreateAuctionRoomRequestDto;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;

import java.util.List;

public interface AuctionService {

    AuctionDomain createAuctionRoom(CreateAuctionRoomRequestDto dto);
    List<AuctionDomain> findByAuctionStatus(AuctionStatus auctionStatus);
    AuctionDomain findByAuctionId(Long auctionId);
    AuctionDomain findByAuctionKey(String auctionKey);

    void updateAuctionStatus(Long auctionId);

    void updateAuctionBidOpen(String auctionKey);
    void updateAuctionBidClose(String auctionKey);

    List<AuctionMessage> findBidHistoryByAuctionKey(String auctionKey);


}
