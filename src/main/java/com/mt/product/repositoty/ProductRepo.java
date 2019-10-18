package com.mt.product.repositoty;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.mt.product.exception.ResourceNotAvailableException;
import com.mt.product.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Component
@PropertySource("classpath:application.properties")
public class ProductRepo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepo.class);
	
	@Value("${filePath}")
	String fileName;
	
	private List<Product> products;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public List<Product> allProducts() {
		try {
			Path myPath = Paths.get(fileName);
			BufferedReader reader = Files.newBufferedReader(myPath,StandardCharsets.UTF_8);
			HeaderColumnNameMappingStrategy<Product> strategy = new HeaderColumnNameMappingStrategy<>();
			strategy.setType(Product.class);

			CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
					.withType(Product.class)
					.withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			products = csvToBean.parse();
			LOGGER.info("Data load successful");
		} catch (IOException e) {
			LOGGER.error("File reading exception raised" + e.getMessage());
			throw new ResourceNotAvailableException("Data Source not available");
		}
		return products;
	}
	
}
