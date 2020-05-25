package org.nbone.modules.sys.service.impl;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.nbone.modules.sys.StorageType;
import org.nbone.modules.sys.entity.Product;
import org.nbone.modules.sys.service.ProductService;
import org.nbone.mvc.service.BaseServiceDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-01-03
 */
@Service
public class ProductServiceImpl extends BaseServiceDomain<Product, Integer> implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${nbone.sysconf.product.key:product}")
    private String productKey;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<Product> getProducts(StorageType storageType) throws Exception {
        List<Product> products = null;
        switch (storageType) {
            case DB:
                products = this.getAll((String) null);
                break;

            case REDIS:

                break;
            case APOLLO:
                Config config = ConfigService.getAppConfig();
                String property = config.getProperty(productKey, null);
                if (StringUtils.isEmpty(property)) {
                    logger.warn("productKey: '" + productKey + "' value be null");
                }
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType;
                if (typeFactory == null) {
                    collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, Product.class);
                }
                collectionType = typeFactory.constructCollectionType(List.class, Product.class);
                if(property == null){
                    return Collections.emptyList();
                }
                products = objectMapper.readValue(property, collectionType);
                break;

            default:
                break;
        }
        return products;
    }
}
