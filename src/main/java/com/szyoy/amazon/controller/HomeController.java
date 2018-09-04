package com.szyoy.amazon.controller;

import com.szyoy.amazon.config.BussinessConfig;
import com.szyoy.amazon.dto.ProductDto;
import com.szyoy.amazon.model.Product;
import com.szyoy.amazon.service.BestSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niange
 * @ClassName: HomeController
 * @desp:
 * @date: 2018/8/20 下午10:51
 * @since JDK 1.7
 */

@RestController
public class HomeController {

    @Autowired
    private BestSaleService bestSaleService;
    @Autowired
    private BussinessConfig bussinessConfig;

    @PostMapping("/products/{pageNo}")
    public String products(@RequestParam("pageNo") int pageNo, ProductDto param){
        List<Product> list = bestSaleService.products(param, pageNo, bussinessConfig.getPageSize());
        long count = bestSaleService.countProducts(param);
        return null;
    }

}
