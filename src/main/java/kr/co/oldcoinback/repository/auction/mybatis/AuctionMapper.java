package kr.co.oldcoinback.repository.auction.mybatis;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.domain.AuctionDomain;
import kr.co.oldcoinback.domain.AuctionMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {

    int insertAuction(AuctionDomain domain);
    int updateAuction(AuctionDomain domain);
    AuctionDomain findByAuctionId(Long auctionId);
    AuctionDomain findByAuctionKey(String auctionKey);
    List<AuctionDomain> findByAuctionStatus(AuctionStatus auctionStatus);
    List<AuctionMessage> findBidHistoryByAuctionKey(String auctionKey);

    int updateAuctionStatus(AuctionDomain auction);

    int updateAuctionBidOpen(String auctionKey);
    int updateAuctionBidClose(String auctionKey);

}
