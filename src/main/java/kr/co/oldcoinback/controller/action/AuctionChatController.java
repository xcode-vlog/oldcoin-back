package kr.co.oldcoinback.controller.action;

import kr.co.oldcoinback.common.enums.AuctionMessageType;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.AuctionMessageEntity;
import kr.co.oldcoinback.domain.request.action.AuctionMessageRequest;
import kr.co.oldcoinback.domain.response.auction.GetAuctionMember;
import kr.co.oldcoinback.domain.response.user.ResolvedUser;
import kr.co.oldcoinback.service.auction.AuctionBidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuctionChatController {

    private final AuctionBidService auctionBidService;

    @MessageMapping("/auction/{auctionKey}") // /pub/auction/
    @SendTo("/sub/auction/{auctionKey}") // /sub/auction/
    public AuctionMessage handlerMessage(Authentication authentication, @DestinationVariable String auctionKey, @Payload AuctionMessageRequest payload) {

        ResolvedUser user = (ResolvedUser) authentication.getPrincipal();

        log.info(payload.toString());


        AuctionMessageEntity entity = AuctionMessageEntity.builder()
                .auctionKey(auctionKey)
                .senderId(user.getUserId())

                .build();

        if(AuctionMessageType.BID_MAX == payload.getAuctionMessageType()) {
            // TODO 최고가 입찰 처리
            entity.setAuctionMessageType(AuctionMessageType.BID_MAX);
            entity.setAuctionPrice(Long.parseLong(payload.getMessage()));
            entity.setAward("Y");
            final boolean bidResult = auctionBidService.processMaxBid(entity);

            return AuctionMessage.builder()
                    .senderId(user.getUserId())
                    .senderName(user.getNickName())
                    .auctionMessageType(bidResult ? AuctionMessageType.BID_SUCCESS : AuctionMessageType.BID_FAIL)
                    .auctionKey(auctionKey)
                    .auctionPrice(entity.getAuctionPrice())
                    .award(entity.getAward())
                    .messageId(entity.getMessageId())
                    .build();
        } else if(AuctionMessageType.BID == payload.getAuctionMessageType()) {
            // TODO 일반입찰
            entity.setAuctionMessageType(AuctionMessageType.BID);
            entity.setAuctionPrice(Long.parseLong(payload.getMessage()));
            entity.setAward("N");
            final boolean bidResult = auctionBidService.processNormallyBid(entity);

            return AuctionMessage.builder()
                    .senderId(user.getUserId())
                    .senderName(user.getNickName())
                    .auctionMessageType(bidResult ? AuctionMessageType.BID_SUCCESS : AuctionMessageType.BID_FAIL)
                    .auctionKey(auctionKey)
                    .auctionPrice(entity.getAuctionPrice())
                    .award(entity.getAward())
                    .messageId(entity.getMessageId())
                    .build();
        } else if(AuctionMessageType.ENTER == payload.getAuctionMessageType()) {
            // TODO 입장
            final List<GetAuctionMember> memberList = auctionBidService.enterAuction(auctionKey, user.getUserId());
            return AuctionMessage.builder()
                    .senderId(user.getUserId())
                    .senderName(user.getNickName())
                    .auctionMessageType(AuctionMessageType.ENTER)
                    .auctionKey(auctionKey)
                    .memberList(memberList)
                    .build();

        } else if(AuctionMessageType.LEAVE == payload.getAuctionMessageType()) {
            // TODO 퇴장
            final List<GetAuctionMember> memberList = auctionBidService.leaveAuction(auctionKey, user.getUserId());
            return AuctionMessage.builder()
                    .senderId(user.getUserId())
                    .senderName(user.getNickName())
                    .auctionMessageType(AuctionMessageType.ENTER)
                    .auctionKey(auctionKey)
                    .memberList(memberList)
                    .build();
        } else if(AuctionMessageType.BID_CLOSE == payload.getAuctionMessageType()) {
            // TODO 경매 종료
            // TODO 최종 응찰자 낙찰 처리
            auctionBidService.updateAwardAuctionMember(auctionKey);
            return AuctionMessage.builder()
                    .auctionMessageType(AuctionMessageType.BID_CLOSE)
                    .auctionKey(auctionKey)
                    .build();


        }


        return AuctionMessage.builder()
                .senderId(user.getUserId())
                .senderName(user.getNickName())
                .auctionMessageType(AuctionMessageType.BID)
                .auctionKey(auctionKey)
                .auctionPrice(Long.parseLong(payload.getMessage()))
                .build();
    }
}
