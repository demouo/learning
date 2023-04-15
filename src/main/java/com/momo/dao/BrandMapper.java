package com.momo.dao;

import com.momo.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();


/**
 * 添加
 */
void  add(Brand brand);

/**
 * 修改数据：先根据id进行查询已经存在的数据  展示 然后再进行新数据的导入
 * 1.参数 id
 * 2.无返回
 */

Brand selectById(int id);

/**
 * 上面的id选出来之后 修改就提交
 */
void update(Brand brand);

/**
 * 删除
 */
void deleteById(int id);
}
