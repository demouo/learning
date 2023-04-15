package com.momo.service;

import com.momo.dao.BrandMapper;
import com.momo.pojo.Brand;
import com.momo.util.sqlsessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    final SqlSessionFactory sqlSessionFactory = sqlsessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有功能
     *
     * @return
     */
    public List<Brand> selectAll(){

       //调用Mapper的selectAll方法
       final SqlSession sqlSession = sqlSessionFactory.openSession();
       final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
       final List<Brand> brands = mapper.selectAll();
       sqlSession.close();
       return brands;


   }

   public  void add(Brand brand){

       final SqlSession sqlSession = sqlSessionFactory.openSession();
       final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
      mapper.add(brand);
      sqlSession.commit();
       sqlSession.close();
   }

    public Brand selectById(int id){

        //调用Mapper的selectAll方法
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        final Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public  void update(Brand brand){
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteById(int id){
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
