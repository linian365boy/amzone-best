package com.szyoy.amazon.dao;

import com.szyoy.amazon.dto.ProductDto;
import com.szyoy.amazon.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author niange
 * @ClassName: ProductDao
 * @desp:
 * @date: 2018/8/20 下午11:24
 * @since JDK 1.7
 */

@Mapper
public interface ProductDao {

    List<Product> findProducts(@Param("param") ProductDto param,@Param("start") int start, @Param("pageSize") int limit);

    long countProducts(ProductDto param);

}
