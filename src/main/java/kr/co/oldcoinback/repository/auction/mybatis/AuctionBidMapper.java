package kr.co.oldcoinback.repository.auction.mybatis;

import kr.co.oldcoinback.domain.AuctionMessageEntity;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AuctionBidMapper {

    Optional<Long> selectMaxBid(String auctionKey);
    Optional<Integer> selectBidPriority(String auctionKey, Long auctionPrice);
    int insertBidMessage(AuctionMessageEntity auctionMessageEntity);


    int enterAuction(String auctionKey, String userId);
    int leaveAuction(String auctionKey, String userId);
    List<GetAuctionMember> selectAuctionMember(String auctionKey);

    int updateAwardAuctionMember(Long messageId);
}
