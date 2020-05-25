package org.nbone.modules.sys.service;

import org.nbone.modules.sys.entity.DictInfo;
import org.nbone.modules.sys.entity.DictValue;
import org.nbone.mvc.service.SuperService;

import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2020-03-05
 */
public interface DictInfoService extends SuperService<DictInfo, Integer> {


    /**
     * 添加字典信息
     *
     * @param dictInfo
     */
    void addDictInfo(DictInfo dictInfo);


    /**
     * 修改字典信息
     *
     * @param dictInfo
     */
    void updateDictInfo(DictInfo dictInfo);


    /**
     * 添加字典值
     *
     * @param dictValue
     */
    void addDictValue(DictValue dictValue);

    /**
     * 添加字典值
     *
     * @param dictValues
     */
    void addDictValues(List<DictValue> dictValues);


    /**
     * 修改字典值
     *
     * @param dictValue
     */
    void updateDictValue(DictValue dictValue);

    /**
     * 修改字典值
     *
     * @param dictValues
     */
    void updateDictValues(List<DictValue> dictValues);


    /**
     * 添加字典值和修改字典值
     *
     * @param dictValues
     */
    void addOrUpdateDictValues(List<DictValue> dictValues);


    /**
     * 根据字典查询字典值列表
     *
     * @param dictId
     * @return
     */
    List<DictValue> getDictValues(Integer dictId, String tableName);

    /**
     * 根据字典查询字典值列表
     *
     * @param dictId     字典id
     * @param fieldNames 需要返回的字段
     * @param tableName  表名称
     * @return
     */
    List<DictValue> getDictValues(Integer dictId, String[] fieldNames, String tableName);

}
