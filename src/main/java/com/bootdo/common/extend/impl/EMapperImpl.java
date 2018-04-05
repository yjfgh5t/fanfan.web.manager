package com.bootdo.common.extend.impl;

import com.bootdo.common.extend.EMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("EMapper")
public class EMapperImpl implements EMapper {

    @Autowired
    Mapper dozerMapper;

    @Override
    public <T,Z> List<T> mapArray(List<Z> listArry, Class<T> classObj) {

        //判断非空
        if(null==listArry || listArry.size()==0) {
            return null;
        }

        List<T> renArry = new ArrayList<>(listArry.size());

        //添加到集合
        for(Z item :listArry)
        {
            renArry.add(dozerMapper.map(item,classObj));
        }

        return renArry;
    }

    @Override
    public <T> T map(Object var1, Class<T> var2) throws MappingException {
        if(var1==null)
            return null;
        return dozerMapper.map(var1,var2);
    }

    @Override
    public void map(Object var1, Object var2) throws MappingException {
        dozerMapper.map(var1,var2);
    }

    @Override
    public <T> T map(Object var1, Class<T> var2, String var3) throws MappingException {
        return  dozerMapper.map(var1,var2,var3);
    }

    @Override
    public void map(Object var1, Object var2, String var3) throws MappingException {
        dozerMapper.map(var1,var2,var3);
    }
}
