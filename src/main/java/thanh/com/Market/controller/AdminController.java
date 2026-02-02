package thanh.com.Market.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import thanh.com.Market.domain.Product;
import thanh.com.Market.domain.ProductCache;
import thanh.com.Market.domain.ProductService;
import thanh.com.Market.domain.UploadService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UploadService uploadService;

    @GetMapping("/create")
    public String getMethodName(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String postMethodName(
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile file) throws IOException {

        // 👉 img bây giờ là URL Cloudinary
        String img = this.uploadService.handleUpLoadFile(file, "image");
        product.setImg(img);

        this.productService.createProduct(product);

        ProductCache.getInstance()
                .loadFromDB(productService.getAll());

        return "redirect:/";
    }

    @GetMapping("/admin")
    public String handleGetAllProducts(Model model) {
        List<Product> ds = productService.getAllProductsFromCache();

        model.addAttribute("ds", ds);

        return "admin-list";
    }

}
