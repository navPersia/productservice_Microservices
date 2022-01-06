package com.shop.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productservice.model.Product;
import com.shop.productservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    private Product product1 = new Product("1","Laptop", "https://picsum.photos/200/300", "Best laptop ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8");
    private Product product2 = new Product("2","CdPlayer", "https://picsum.photos/200/300", "Best CdPlayer ever made", Boolean.TRUE,"6179c6e5fd092603c4b986aa");
    private Product product3 = new Product("3","Glass", "https://picsum.photos/200/300", "Best Glass ever made", Boolean.TRUE,"6179c6e5fd092603c4b986aa");

    @BeforeEach
    public void beforeAllTests() {
        productRepository.deleteAll();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        productRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testproductid() throws Exception {

        mockMvc.perform(get("/products/product/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Laptop")))
                .andExpect(jsonPath("$.imgUrl", is("https://picsum.photos/200/300")))
                .andExpect(jsonPath("$.description", is("Best laptop ever made since 1995")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.userId", is("6179c6e5fd092603c4b986a8")));
    }

    @Test
    public void postUser() throws Exception {
        Product newProduct = new Product("100","Shampoo", "https://picsum.photos/200/300", "Best Shampoo ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8");

        mockMvc.perform(post("/products/product")
                        .content(mapper.writeValueAsString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Shampoo")))
                .andExpect(jsonPath("$.imgUrl", is("https://picsum.photos/200/300")))
                .andExpect(jsonPath("$.description", is("Best Shampoo ever made since 1995")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.userId", is("6179c6e5fd092603c4b986a8")));
    }

    @Test
    public void putUser() throws Exception {

        Product newProduct = new Product("1","Tattoo", "https://picsum.photos/200/300", "Best Tattoo ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8");

        mockMvc.perform(put("/products/product/1")
                        .content(mapper.writeValueAsString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Tattoo")))
                .andExpect(jsonPath("$.imgUrl", is("https://picsum.photos/200/300")))
                .andExpect(jsonPath("$.description", is("Best Tattoo ever made since 1995")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.userId", is("6179c6e5fd092603c4b986a8")));
    }

    @Test
    public void deleteUser() throws Exception {

        mockMvc.perform(delete("/products/product/{id}", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
