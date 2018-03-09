package wyx.manager.extend;

import org.dozer.MappingException;

import java.util.List;

public interface EMapper {

    /**
     * 集合转换
     * @param listArry
     * @param classObj
     * @param <T>
     * @return
     */
    <T,Z> List<T> mapArray(List<Z> listArry,Class<T> classObj);

    <T> T map(Object var1, Class<T> var2) throws MappingException;

    void map(Object var1, Object var2) throws MappingException;

    <T> T map(Object var1, Class<T> var2, String var3) throws MappingException;

    void map(Object var1, Object var2, String var3) throws MappingException;

}
