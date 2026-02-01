package thanh.com.Market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import thanh.com.Market.domain.Product;
import thanh.com.Market.domain.ProductCache;
import thanh.com.Market.domain.ProductService;

@Component
public class ProductCacheInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) {
        List<Product> list = productService.getAll();
        System.out.println("INIT CACHE SIZE = " + list.size());

        ProductCache.getInstance().loadFromDB(list);
    }
}
