package thanh.com.Market.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductCache {

    private static ProductCache instance;
    private List<Product> products;

    private ProductCache() {
        products = new ArrayList<>();
    }

    public static synchronized ProductCache getInstance() {
        if (instance == null) {
            instance = new ProductCache();
        }
        return instance;
    }

    public synchronized List<Product> getAll() {
        return products;
    }

    public synchronized void loadFromDB(List<Product> dbProducts) {
        this.products = dbProducts;
    }

    public synchronized void clear() {
        products.clear();
    }
}
