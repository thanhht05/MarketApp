// package thanh.com.Market.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfigure implements WebMvcConfigurer {
// @Value("${upoadDir}")
// private String uploadDir;

// @Override
// public void addResourceHandlers(ResourceHandlerRegistry registry) {
// registry.addResourceHandler("/images/**") // URL client gọi
// .addResourceLocations(uploadDir); // Folder/file thật map với URL ciletn gọi

// }
// }
