package org.nbone.modules.sys.service;

import org.nbone.modules.sys.StorageType;
import org.nbone.modules.sys.entity.Product;
import org.nbone.mvc.service.SuperService;

import java.util.List;

/**
 * 产品管理服务
 *
 * @author thinking
 * @version 1.0
 * @since 2020-01-02
 */
public interface ProductService extends SuperService<Product, Integer> {


    /**
     * 获取全部的产品列表
     *
     * @param storageType
     * @return
     */
    public List<Product> getProducts(StorageType storageType) throws Exception;


}
