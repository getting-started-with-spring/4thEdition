package sample.spring.chapter10.bankapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<ConcurrentMapCache> caches = new ArrayList<>();
		caches.add(fixedDepositListCache().getObject());
		caches.add(fixedDepositCache().getObject());
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean fixedDepositListCache() {
		ConcurrentMapCacheFactoryBean fixedDepositListBean = new ConcurrentMapCacheFactoryBean();
		fixedDepositListBean.setName("fixedDepositList");
		return fixedDepositListBean;
	}

	@Bean
	public ConcurrentMapCacheFactoryBean fixedDepositCache() {
		ConcurrentMapCacheFactoryBean fixedDepositBean = new ConcurrentMapCacheFactoryBean();
		fixedDepositBean.setBeanName("fixedDeposit");
		return fixedDepositBean;
	}

}
