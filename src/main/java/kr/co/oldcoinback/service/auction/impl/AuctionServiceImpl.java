package kr.co.oldcoinback.service.auction.impl;

import kr.co.oldcoinback.common.enums.AuctionStatus;
import kr.co.oldcoinback.domain.AuctionDomain;
import kr.co.oldcoinback.domain.AuctionMessage;
import kr.co.oldcoinback.domain.request.action.CreateAuctionRoomRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import kr.co.oldcoinback.repository.auction.AuctionRepository;
import kr.co.oldcoinback.repository.product.ProductRepository;
import kr.co.oldcoinback.service.auction.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ProductRepository productRepository;


    private void findProductInfo(List<AuctionDomain> list) {
        list.parallelStream().forEach(auctionDomain -> {
            Long productId = auctionDomain.getProductId();
            final GetProductInfoResponse productInfo = productRepository.getProductInfo(productId);
            auctionDomain.setProductInfo(productInfo);
        });
    };



    @Override
    public AuctionDomain createAuctionRoom(CreateAuctionRoomRequestDto dto) {
        String auctionKey = UUID.randomUUID().toString();
        AuctionDomain auction = AuctionDomain.builder()
                .auctionKey(auctionKey)
                .productId(dto.getProductId())
                .status(AuctionStatus.OPEN)
                .build();
        auctionRepository.insertAuction(auction);

        return auction;
    }

    @Override
    public List<AuctionDomain> findByAuctionStatus(AuctionStatus auctionStatus) {
        final List<AuctionDomain> auctionList = auctionRepository.findByAuctionStatus(auctionStatus);
        findProductInfo(auctionList);
        return auctionList;

    }

    @Override
    public AuctionDomain findByAuctionId(Long auctionId) {
        final AuctionDomain auction = auctionRepository.findByAuctionId(auctionId);

        Long productId = auction.getProductId();
        final GetProductInfoResponse productInfo = productRepository.getProductInfo(productId);
        auction.setProductInfo(productInfo);

        return auction;
    }
    @Override
    public AuctionDomain findByAuctionKey(String auctionKey) {
        final AuctionDomain auction = auctionRepository.findByAuctionKey(auctionKey);

        Long productId = auction.getProductId();
        final GetProductInfoResponse productInfo = productRepository.getProductInfo(productId);
        auction.setProductInfo(productInfo);

        return auction;
    }

    @Override
    public void updateAuctionStatus(Long auctionId) {
        AuctionDomain auction = AuctionDomain.builder()
                .auctionId(auctionId)
                .status(AuctionStatus.CLOSE)
                .build();

        auctionRepository.updateAuctionStatus(auction);
    }

    @Override
    public void updateAuctionBidOpen(String auctionKey) {
        auctionRepository.updateAuctionBidOpen(auctionKey);
    }

    @Override
    public void updateAuctionBidClose(String auctionKey) {
        auctionRepository.updateAuctionBidClose(auctionKey);
    }

    @Override
    public List<AuctionMessage> findBidHistoryByAuctionKey(String auctionKey) {
        return auctionRepository.findBidHistoryByAuctionKey(auctionKey);
    }


}
