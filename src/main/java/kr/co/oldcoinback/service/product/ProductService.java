package kr.co.oldcoinback.service.product;

import kr.co.oldcoinback.domain.request.product.CreateProductRequestDto;
import kr.co.oldcoinback.domain.request.product.UpdateProductRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;

import java.util.List;

public interface ProductService {

    int createProduct(CreateProductRequestDto dto);
    int updateProduct(UpdateProductRequestDto dto);
    GetProductInfoResponse getProductInfo(Long productId);
    List<GetProductInfoResponse> getAllProduct();
}
