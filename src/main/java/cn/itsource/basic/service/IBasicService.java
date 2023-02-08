package cn.itsource.basic.service;

import cn.itsource.basic.uitl.PageList;
import cn.itsource.org.domain.Department;
import cn.itsource.org.domain.Employee;
import cn.itsource.org.query.EmployeeQuery;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/7 19:45
 * 描述：
 */
public interface IBasicService<T, Q> {

    /**
     * 分页查询
     * @param q  分页信息和数据
     * @return 分页＋表数据
     */
    PageList<T> findPage(Q q);



    /**
     * 用户id查询单条数据
     * @param id 用户id
     * @return 返回一条数据
     */
    T findOne(Long id);

    /**
     * 全部员工数据
     * @return id和表数据
     */
    List<T> findAll();

    /**
     * 新增/修改接口
     * @param t 对象
     */
    void save(T t);

    /**
     * 删除方法
     * @param id 主键id
     */
    void delete(Long id);

    /**
     * 删除方法
     * @param ids 一堆主键id
     */
    void deleteMuch(Long[] ids);
}
