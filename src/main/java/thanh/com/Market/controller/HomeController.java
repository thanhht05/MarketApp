package thanh.com.Market.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import thanh.com.Market.domain.Cart;
import thanh.com.Market.domain.Product;
import thanh.com.Market.domain.ProductService;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHomePage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = this.productService.getProductByName(keyword);
        } else {
            products = productService.getAllProductsFromCache();
        }
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);

        return "home";
    }

    @GetMapping("/product/{id}")
    public String addProductToCart(@PathVariable Long id,
            HttpSession session,
            Model model) {

        List<Cart> carts = (List<Cart>) session.getAttribute("cartItems");
        if (carts == null) {
            carts = new ArrayList<>();
        }

        Product p = productService.getById(id);

        boolean found = false;
        for (Cart c : carts) {
            if (c.getProductId().equals(id)) {
                c.setQuantity(c.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            Cart newCart = new Cart();
            newCart.setProductId(p.getId());
            newCart.setName(p.getName());
            newCart.setPrice(p.getPrice());
            newCart.setQuantity(1);
            carts.add(newCart);
        }

        session.setAttribute("cartItems", carts);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getMethodName(Model model, HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("cartItems");
        if (carts == null) {
            carts = new ArrayList<>();
        }
        int totalPrice = 0;
        for (Cart c : carts) {
            totalPrice += c.getTotal();
        }
        model.addAttribute("cartItems", carts);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @GetMapping("/deleteCart")
    public String deleteCart(HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("cartItems");
        if (carts != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
