package kr.co.oldcoinback.controller.action;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.common.response.ResponseModel;
import kr.co.oldcoinback.domain.AuctionDomain;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.request.action.CreateAuctionRoomRequestDto;
import kr.co.oldcoinback.service.auction.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auction")
public class AuctionController {
    private final AuctionService auctionService;

    @PostMapping("/create")
    public ResponseModel<AuctionDomain> createAuction(@RequestBody CreateAuctionRoomRequestDto dto) {

        final AuctionDomain auctionRoom = auctionService.createAuctionRoom(dto);
        return ResponseModel.OK(auctionRoom);
    }

    @GetMapping("/active")
    public ResponseModel<List<AuctionDomain>> getActiveAuction() {
        final List<AuctionDomain> auctionList = auctionService.findByAuctionStatus(AuctionStatus.OPEN);
        return ResponseModel.OK(auctionList);
    }
    @GetMapping("/inactive")
    public ResponseModel<List<AuctionDomain>> getInactiveAuction() {
        final List<AuctionDomain> auctionList = auctionService.findByAuctionStatus(AuctionStatus.CLOSE);
        return ResponseModel.OK(auctionList);
    }

    @DeleteMapping("/action-inactive/{auctionId}")
    public ResponseModel<Void> deleteAuction(@PathVariable Long auctionId) {
        auctionService.updateAuctionStatus(auctionId);
        return ResponseModel.OK(null);
    }

    @GetMapping("/detail/{auctionKey}")
    public ResponseModel<AuctionDomain> getAuctionDetail(@PathVariable String auctionKey) {
        final AuctionDomain auction = auctionService.findByAuctionKey(auctionKey);
        return ResponseModel.OK(auction);
    }
    @GetMapping("/bid-history/{auctionKey}")
    public ResponseModel<List<AuctionMessage>> getBidHistory(@PathVariable String auctionKey) {
        final List<AuctionMessage> auctionMessages = auctionService.findBidHistoryByAuctionKey(auctionKey);
        return ResponseModel.OK(auctionMessages);
    }

    @PutMapping("/bid-open/{auctionKey}")
    public ResponseModel<Void> updateBidOpen(@PathVariable String auctionKey) {
        auctionService.updateAuctionBidOpen(auctionKey);
        // TODO 응찰 시작 메세지 전달
        return ResponseModel.OK(null);
    }
    @PutMapping("/bid-close/{auctionKey}")
    public ResponseModel<Void> updateBidClose(@PathVariable String auctionKey) {
        auctionService.updateAuctionBidClose(auctionKey);
        // TODO 응찰 종료 메세지 전달
        return ResponseModel.OK(null);
    }
}
