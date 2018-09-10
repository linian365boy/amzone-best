package com.szyoy.amazon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szyoy.amazon.config.BussinessConfig;
import com.szyoy.amazon.dto.PageDto;
import com.szyoy.amazon.dto.ProductDto;
import com.szyoy.amazon.model.Product;
import com.szyoy.amazon.service.BestSaleService;
import SortTypeEnum;

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
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @PostMapping("/products/{pageNo}")
    public PageDto<Product> products(@PathVariable("pageNo") int pageNo, ProductDto param){
    	logger.info("products request param, pageNo => {}, param => {}", pageNo, param);
    	int pageSize = bussinessConfig.getSize();
    	long count = bestSaleService.countProducts(param);
    	long maxPageNo = (count%pageSize==0 ? (count/pageSize) : (count/pageSize) + 1);
    	if(pageNo > maxPageNo || pageNo < 1) {
    		pageNo = 1;
    	}
    	if(!SortTypeEnum.getNames().contains(param.getSortBy())) {
    		param.setSortBy("id");
    	}
    	logger.info("query db param=>{}, pageNo=>{}, pageSize=>{}", param, pageNo, pageSize);
        List<Product> list = bestSaleService.products(param, pageNo, pageSize);
        PageDto<Product> dto = new PageDto<>(list, pageNo, maxPageNo, count);
        logger.info("products return result => {}", dto);
        return dto;
    }

}
