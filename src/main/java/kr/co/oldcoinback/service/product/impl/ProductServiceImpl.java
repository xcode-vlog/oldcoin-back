package kr.co.oldcoinback.service.product.impl;

import kr.co.oldcoinback.domain.request.product.CreateProductRequestDto;
import kr.co.oldcoinback.domain.request.product.UpdateProductRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import kr.co.oldcoinback.repository.product.ProductRepository;
import kr.co.oldcoinback.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public int createProduct(CreateProductRequestDto dto) {
        return productRepository.insertProduct(dto);
    }

    @Override
    public int updateProduct(UpdateProductRequestDto dto) {
        return productRepository.updateProduct(dto);
    }

    @Override
    public GetProductInfoResponse getProductInfo(Long productId) {
        return productRepository.getProductInfo(productId);
    }

    @Override
    public List<GetProductInfoResponse> getAllProduct() {
        return productRepository.getAllProduct();
    }


}
