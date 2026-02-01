package thanh.com.Market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import thanh.com.Market.domain.ProductCache;
import thanh.com.Market.domain.ProductService;

@Component
public class ProductCacheInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) {
        ProductCache.getInstance()
                .loadFromDB(productService.getAll());
    }
}
