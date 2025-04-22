package kr.co.oldcoinback.repository.product.mybatis;

import kr.co.oldcoinback.domain.request.product.CreateProductRequestDto;
import kr.co.oldcoinback.domain.request.product.UpdateProductRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(CreateProductRequestDto dto);
    int updateProduct(UpdateProductRequestDto dto);
    GetProductInfoResponse findByProductId(Long productId);
    List<GetProductInfoResponse> findAllProduct();
}
