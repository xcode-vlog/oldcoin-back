package kr.co.oldcoinback.controller.product;

import kr.co.oldcoinback.common.response.ResponseModel;
import kr.co.oldcoinback.domain.request.product.CreateProductRequestDto;
import kr.co.oldcoinback.domain.request.product.UpdateProductRequestDto;
import kr.co.oldcoinback.domain.response.product.GetProductInfoResponse;
import kr.co.oldcoinback.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create-product")
    public ResponseModel<Void> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        productService.createProduct(requestDto);
        return ResponseModel.OK(null);
    }

    @PostMapping("/update-product")
    public ResponseModel<Void> updateProduct(@RequestBody UpdateProductRequestDto requestDto) {
        productService.updateProduct(requestDto);
        return ResponseModel.OK(null);
    }

    @GetMapping("/product-info/{productId}")
    public ResponseModel<GetProductInfoResponse> productInfo(@PathVariable Long productId) {
        final GetProductInfoResponse productInfo = productService.getProductInfo(productId);
        return ResponseModel.OK(productInfo);
    }

    @GetMapping("/product-list")
    public ResponseModel<List<GetProductInfoResponse>> productList() {
        final List<GetProductInfoResponse> allProduct = productService.getAllProduct();
        return ResponseModel.OK(allProduct);
    }
}
