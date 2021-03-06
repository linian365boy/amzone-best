package com.szyoy.amazon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author niange
 * @ClassName: BussinessConfig
 * @desp:
 * @date: 2018/8/20 下午11:42
 * @since JDK 1.7
 */

@Configuration
@ConfigurationProperties(prefix = "page")
public class BussinessConfig {
    private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
