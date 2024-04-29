package dev.dharamesh.ecomproductservice.client;

import dev.dharamesh.ecomproductservice.dto.FakeStoreProductRequestDto;
import dev.dharamesh.ecomproductservice.dto.FakeStoreProductResponseDto;
import dev.dharamesh.ecomproductservice.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakeStore.api.base.url}")
    private String fakeStoreAPIBaseUrl;
    @Value("${fakestore.api.product.path}")
    private String fakeStoreAPIProductPath;


    public List<FakeStoreProductResponseDto> getAllProducts(){
        String fakeStoreGetAllProductsURL = fakeStoreAPIBaseUrl.concat(fakeStoreAPIProductPath);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProductResponseList =
                restTemplate.getForEntity(fakeStoreGetAllProductsURL, FakeStoreProductResponseDto[].class);

        return List.of(fakeStoreProductResponseList.getBody());
    }

    public FakeStoreProductResponseDto getProductById(int id){
        String fakeStoreGetProductURL = fakeStoreAPIBaseUrl.concat(fakeStoreAPIProductPath).concat("/"+id);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStroreProduct =
                restTemplate.getForEntity(fakeStoreGetProductURL, FakeStoreProductResponseDto.class);

        return fakeStroreProduct.getBody();
    }

    public FakeStoreProductResponseDto createProduct(FakeStoreProductRequestDto requestDto){
        String fakeStoreCreateProductURL = fakeStoreAPIBaseUrl.concat(fakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> createdFakeStoreProduct =
                restTemplate.postForEntity(fakeStoreCreateProductURL,requestDto, FakeStoreProductResponseDto.class);

        return createdFakeStoreProduct.getBody();
    }
}
