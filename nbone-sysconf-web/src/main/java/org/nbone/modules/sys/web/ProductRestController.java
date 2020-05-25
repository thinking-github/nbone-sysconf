package org.nbone.modules.sys.web;

import io.swagger.annotations.Api;
import org.nbone.framework.spring.web.bind.annotation.ResultResponseBody;
import org.nbone.framework.spring.web.util.CacheControlUtils;
import org.nbone.modules.sys.StorageType;
import org.nbone.modules.sys.entity.Product;
import org.nbone.modules.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.GetPostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-01-02
 */
@Api(tags = {"产品列表"})
@RestController
@RequestMapping(value = {"${nbone.sysconf.base.path:sys}"})
public class ProductRestController {

    @Autowired
    private ProductService productService;
    /**
     * TimeUnit.HOURS
     */
    private final static long maxAge = 3;

    private static StorageType initialized = StorageType.DB;

    /**
     * 自定义实现产品列表
     *
     * @param request
     * @param response
     * @return
     */
    protected List<Product> customProducts(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @GetPostMapping(value = "products", produces = "application/json")
    @ResultResponseBody
    public List<Product> products(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> products = customProducts(request, response);
        if (products == null) {
            products = productService.getProducts(initialized);
        }
        CacheControlUtils.applyCache(response, CacheControl.maxAge(maxAge, TimeUnit.HOURS).cachePublic());
        return products;
    }

    public final static void initialize(StorageType storageType) {
        initialized = storageType;
    }
}
