package wyx.manager.extend.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyx.manager.extend.EMapper;

import java.util.ArrayList;
import java.util.List;

@Service("EMapper")
public class EMapperImpl implements EMapper {

    @Autowired
    Mapper mapper;

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
            renArry.add(mapper.map(item,classObj));
        }

        return renArry;
    }

    @Override
    public <T> T map(Object var1, Class<T> var2) throws MappingException {
        return mapper.map(var1,var2);
    }

    @Override
    public void map(Object var1, Object var2) throws MappingException {
        mapper.map(var1,var2);
    }

    @Override
    public <T> T map(Object var1, Class<T> var2, String var3) throws MappingException {
        return  mapper.map(var1,var2,var3);
    }

    @Override
    public void map(Object var1, Object var2, String var3) throws MappingException {
        mapper.map(var1,var2,var3);
    }
}
