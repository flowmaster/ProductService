package com.mt.product.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mt.product.model.Product;
import com.mt.product.service.ProductService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class,secure=false)
public class ProductControllerTest {
	
	/**
	 * This stipulate to have a exception assertion rule .
	 */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Invoke the before advice of the Mockito for initiation
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	}
	
	/**
	 * Inject mockmvc 
	 */
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Inject authentication service as a mock bean
	 */
	@MockBean
	ProductService productService;
	
	/**
	 * Injecting controller to serve end point 
	 */
	@InjectMocks
	ProductController controller;
	
	private ObjectMapper mapper = null;
	
	@Test
    public void productLookupTest() throws Exception {
		
		Product product = createProduct();
        Mockito.when(
        		productService.findById(Mockito.any(Integer.class))).thenReturn(product);
        String json = mapper.writeValueAsString(product);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/mytoys/product/12341")
				.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(result.getResponse().getContentAsString(), json);
    }
	
	@Test
    public void allProductTest() throws Exception {
		
		Product product = createProduct();
		Product productLeo = createProduct();
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		products.add(productLeo);
		Mockito.when(
        		productService.findAll()).thenReturn(products);
        String json = mapper.writeValueAsString(products);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/mytoys/products")
				.contentType(MediaType.APPLICATION_JSON);
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(result.getResponse().getContentAsString(), json);
    }
	
	private Product createProduct(){
		Random rand = new Random();
		Product product = new Product();
		product.setProductId(12341+rand.nextInt(9));
		product.setProductName("Leo");
		product.setOldPrice(26.32+rand.nextInt(9));
		product.setPrice(56.32+rand.nextInt(9));
		product.setBrand("Leo");
		product.setStock(26);
		
		return product ;
	}
	
}
