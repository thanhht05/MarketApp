package thanh.com.Market.domain;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public List<Product> getProductByName(String name) {

        return productRepository.findByNameContainingIgnoreCase(name);

    }

    public Product getById(Long id) {
        Optional<Product> p = this.productRepository.findById(id);
        if (p.isPresent()) {
            return p.get();
        }
        return null;
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    public String removeAccent(String s) {
        if (s == null)
            return "";
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp)
                .replaceAll("")
                .replace("đ", "d")
                .replace("Đ", "D")
                .toLowerCase()
                .trim();
    }

    public List<Product> getAllProductsFromCache() {
        return ProductCache.getInstance().getAll();
    }

}
