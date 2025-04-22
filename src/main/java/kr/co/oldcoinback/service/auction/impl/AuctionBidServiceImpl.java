package kr.co.oldcoinback.service.auction.impl;

import kr.co.oldcoinback.common.enums.AuctionMessageType;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.AuctionMessageEntity;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;
import kr.co.oldcoinback.repository.auction.AuctionBidRepository;
import kr.co.oldcoinback.repository.auction.AuctionRepository;
import kr.co.oldcoinback.service.auction.AuctionBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionBidServiceImpl implements AuctionBidService {
    private final AuctionBidRepository auctionBidRepository;
    private final AuctionRepository auctionRepository;

    private void saveBidMessage(AuctionMessageEntity auctionMessageEntity) {

        // TODO 2 -> 우선순위 확인
        final Integer priority = auctionBidRepository.selectBidPriority(auctionMessageEntity.getAuctionKey(), auctionMessageEntity.getAuctionPrice());
        auctionMessageEntity.setPriority(priority);
        if(priority != 0) {
            auctionMessageEntity.setAward("N");
        }
        // TODO 3 -> 메세지 저장
        auctionBidRepository.insertBidMessage(auctionMessageEntity);
    }

    @Override
    @Transactional
    synchronized public boolean processMaxBid(AuctionMessageEntity auctionMessageEntity) {
        // TODO 1 -> 최종입찰가 확인
        final Long maxBid = auctionBidRepository.selectMaxBid(auctionMessageEntity.getAuctionKey());
        // TODO 저장처리
        this.saveBidMessage(auctionMessageEntity);

        // TODO 4 -> 응찰 종료처리
        auctionRepository.updateAuctionBidClose(auctionMessageEntity.getAuctionKey());

        if(maxBid < auctionMessageEntity.getAuctionPrice() && auctionMessageEntity.getPriority() == 0) {
            // 정상
            return true;
        } else {
            // TODO 응찰이 늦었을때
            return false;
        }
    }

    @Override
    @Transactional
    synchronized public boolean processNormallyBid(AuctionMessageEntity auctionMessageEntity) {
        // TODO 1 -> 최종입찰가 확인
        final Long maxBid = auctionBidRepository.selectMaxBid(auctionMessageEntity.getAuctionKey());

        this.saveBidMessage(auctionMessageEntity);

        if(maxBid < auctionMessageEntity.getAuctionPrice() && auctionMessageEntity.getPriority() == 0) {
            // 정상
            return true;
        } else {
            // TODO 응찰이 늦었을때
            return false;
        }
    }

    @Override
    public List<GetAuctionMember> enterAuction(String auctionKey, String userId) {

        List<GetAuctionMember> auctionMembers = auctionBidRepository.selectAuctionMessage(auctionKey);
        final Optional<GetAuctionMember> optionalIsAlreadyEnter = auctionMembers.stream().filter(member -> member.getUserId().equals(userId)).findFirst();

        if(optionalIsAlreadyEnter.isEmpty()) {
            auctionBidRepository.enterAuction(auctionKey, userId);
            auctionMembers = auctionBidRepository.selectAuctionMessage(auctionKey);
        }

        return auctionMembers;
    }

    @Override
    public List<GetAuctionMember> leaveAuction(String auctionKey, String userId) {
        List<GetAuctionMember> auctionMembers = auctionBidRepository.selectAuctionMessage(auctionKey);
        final Optional<GetAuctionMember> optionalIsAlreadyEnter = auctionMembers.stream().filter(member -> member.getUserId().equals(userId)).findFirst();
        if(optionalIsAlreadyEnter.isPresent()) {
            auctionBidRepository.leaveAuction(auctionKey, userId);
            auctionMembers = auctionBidRepository.selectAuctionMessage(auctionKey);
        }
        return auctionMembers;
    }

    @Override
    public List<AuctionMessage> updateAwardAuctionMember(String auctionKey) {
        final List<AuctionMessage> bidHistory = auctionRepository.findBidHistoryByAuctionKey(auctionKey);
        final AuctionMessage targetMessage = bidHistory.getLast();
        auctionBidRepository.updateAwardAuctionMember(targetMessage.getMessageId());
        targetMessage.setAward("Y");

        return bidHistory;
    }


}
