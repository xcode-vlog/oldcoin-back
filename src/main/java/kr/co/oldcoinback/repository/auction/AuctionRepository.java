package kr.co.oldcoinback.repository.auction;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.domain.AuctionDomain;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.request.action.CreateAuctionRoomRequestDto;
import kr.co.oldcoinback.repository.auction.mybatis.AuctionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionRepository {
    private final AuctionMapper auctionMapper;

    public int insertAuction(AuctionDomain auction) {
        return auctionMapper.insertAuction(auction);
    }
    public List<AuctionDomain> findByAuctionStatus(AuctionStatus auctionStatus) {
        return auctionMapper.findByAuctionStatus(auctionStatus);
    }
    public AuctionDomain findByAuctionId(Long auctionId) {
        return auctionMapper.findByAuctionId(auctionId);
    }
    public AuctionDomain findByAuctionKey(String auctionKey) {
        return auctionMapper.findByAuctionKey(auctionKey);
    }

    public int updateAuctionStatus(AuctionDomain auction) {
        return auctionMapper.updateAuctionStatus(auction);
    }

    public int updateAuctionBidOpen(String auctionKey) {
        return auctionMapper.updateAuctionBidOpen(auctionKey);
    }
    public int updateAuctionBidClose(String auctionKey) {
        return auctionMapper.updateAuctionBidClose(auctionKey);
    }

    public List<AuctionMessage> findBidHistoryByAuctionKey(String auctionKey) {
        return auctionMapper.findBidHistoryByAuctionKey(auctionKey);
    }


}
