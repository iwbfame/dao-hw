package com.example.DAO;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String fetchProductScript;

    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             @Value("classpath:fetchProduct.sql") String fetchProductScript) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.fetchProductScript = read(fetchProductScript);
    }

    public List<String> getProductNames(String name) {
        String sql = fetchProductScript;
        Map<String, Object> params = Map.of("name", name);
        return namedParameterJdbcTemplate.queryForList(sql, params, String.class);
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

