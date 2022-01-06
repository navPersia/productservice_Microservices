package com.shop.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productservice.model.Product;
import com.shop.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testproductid() throws Exception {

        Product product1 = new Product("1","Laptop", "https://picsum.photos/200/300", "Best laptop ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8");


        given(productRepository.findById("1")).willReturn(java.util.Optional.of(product1));

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
        given(productRepository.findById("1")).willReturn(java.util.Optional.of(newProduct));

        Product updatedUser = new Product("1","CdPlayer", "https://picsum.photos/200/300", "Best CdPlayer ever made", Boolean.TRUE,"");

        mockMvc.perform(put("/products/product/1")
                        .content(mapper.writeValueAsString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("CdPlayer")))
                .andExpect(jsonPath("$.imgUrl", is("https://picsum.photos/200/300")))
                .andExpect(jsonPath("$.description", is("Best CdPlayer ever made")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.userId", is("6179c6e5fd092603c4b986a8")));
    }

    @Test
    public void deleteUser() throws Exception {

        Product newProduct = new Product("100","Tattoo", "https://picsum.photos/200/300", "Best Tattoo ever made since 1995", Boolean.TRUE,"6179c6e5fd092603c4b986a8");


        given(productRepository.findById("100")).willReturn(java.util.Optional.of(newProduct));

        mockMvc.perform(delete("/products/product/{id}", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
