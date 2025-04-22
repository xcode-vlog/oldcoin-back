package kr.co.oldcoinback.repository.product;

import kr.co.oldcoinback.domain.request.product.CreateProductRequestDto;
import kr.co.oldcoinback.domain.request.product.UpdateProductRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import kr.co.oldcoinback.repository.product.mybatis.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductMapper productMapper;

    public int insertProduct(CreateProductRequestDto dto) {
        return productMapper.insertProduct(dto);
    }
    public int updateProduct(UpdateProductRequestDto dto) {
        return productMapper.updateProduct(dto);
    }

    public GetProductInfoResponse getProductInfo(Long productId) {
        return productMapper.findByProductId(productId);
    }

    public List<GetProductInfoResponse> getAllProduct() {
        return productMapper.findAllProduct();
    }
}
