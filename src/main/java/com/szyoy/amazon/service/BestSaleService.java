package com.szyoy.amazon.service;

import com.szyoy.amazon.dao.ProductDao;
import com.szyoy.amazon.dto.ProductDto;
import com.szyoy.amazon.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niange
 * @ClassName: BestSaleService
 * @desp:
 * @date: 2018/8/20 下午10:54
 * @since JDK 1.7
 */

@Service
public class BestSaleService {

    @Autowired
    private ProductDao productDao;

    public List<Product> products(ProductDto param, int pageNo, int limit){
        int start = (pageNo-1) * limit;
        List<Product> products = productDao.findProducts(param, start, limit);
        return products;
    }

    public long countProducts(ProductDto param){
        return productDao.countProducts(param);
    }

}
