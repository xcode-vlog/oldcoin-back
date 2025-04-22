package kr.co.oldcoinback.repository.auction;

import kr.co.oldcoinback.domain.AuctionMessageEntity;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;
import kr.co.oldcoinback.repository.auction.mybatis.AuctionBidMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionBidRepository {

    private final AuctionBidMapper auctionBidMapper;

    public Long selectMaxBid(String auctionKey) {
        final Optional<Long> optionalMaxBid = auctionBidMapper.selectMaxBid(auctionKey);
        return optionalMaxBid.orElseGet(() -> 0L);
    }

    public Integer selectBidPriority(String auctionKey, Long auctionPrice) {
        final Optional<Integer> optionalMaxBidPriority = auctionBidMapper.selectBidPriority(auctionKey, auctionPrice);
        return optionalMaxBidPriority.orElse(0);
    }

    public int insertBidMessage(AuctionMessageEntity auctionMessageEntity) {
        return auctionBidMapper.insertBidMessage(auctionMessageEntity);
    }

    public int enterAuction(String auctionKey, String userId) {
        return auctionBidMapper.enterAuction(auctionKey, userId);
    }
    public int leaveAuction(String auctionKey, String userId) {
        return auctionBidMapper.leaveAuction(auctionKey, userId);
    }
    public List<GetAuctionMember> selectAuctionMessage(String auctionKey) {
        return auctionBidMapper.selectAuctionMember(auctionKey);
    }

    public int updateAwardAuctionMember(Long messageId) {
        return auctionBidMapper.updateAwardAuctionMember(messageId);
    }

}
