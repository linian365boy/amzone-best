package com.szyoy.amazon.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.szyoy.amazon.config.BussinessConfig;
import com.szyoy.amazon.dto.PageDto;
import com.szyoy.amazon.dto.ProductDto;
import com.szyoy.amazon.model.Product;
import com.szyoy.amazon.service.BestSaleService;
import SortTypeEnum;

@Controller
public class HomeUIController {
	
	@Autowired
    private BestSaleService bestSaleService;
    @Autowired
    private BussinessConfig bussinessConfig;
	private static final Logger logger = LoggerFactory.getLogger(HomeUIController.class);
	
	@RequestMapping(value = {"", "/", "/index", "/home"}, method = RequestMethod.GET)
    public String home(ModelMap map) {
		logger.debug("enter home page");
		//查询第一页的数据
		PageDto<Product> dto = products(1, new ProductDto());
		map.put("dto", dto);
        return "index";
    }
	
    private PageDto<Product> products(int pageNo, ProductDto param){
    	logger.info("home products request param, pageNo => {}, param => {}", pageNo, param);
    	int pageSize = bussinessConfig.getSize();
    	long count = bestSaleService.countProducts(param);
    	long maxPageNo = (count%pageSize==0 ? (count/pageSize) : (count/pageSize) + 1);
    	if(pageNo > maxPageNo || pageNo < 1) {
    		pageNo = 1;
    	}
    	if(!SortTypeEnum.getNames().contains(param.getSortBy())) {
    		param.setSortBy("id");
    	}
    	logger.info("home query db param=>{}, pageNo=>{}, pageSize=>{}", param, pageNo, pageSize);
        List<Product> list = bestSaleService.products(param, pageNo, pageSize);
        PageDto<Product> dto = new PageDto<>(list, pageNo, maxPageNo, count);
        logger.info("home products return result => {}", dto);
        return dto;
    }
	
}
