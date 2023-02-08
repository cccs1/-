package cn.itsource.org.service;

import cn.itsource.basic.service.IBasicService;
import cn.itsource.basic.uitl.PageList;
import cn.itsource.org.domain.Department;
import cn.itsource.org.query.DepartmentQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/4 14:57
 * 描述：
 */
public interface IDepartmentService extends IBasicService<Department, DepartmentQuery> {

    /**
     * 分页接口
     * @param query 页面数据
     * @return 分页加表数据
     */
    PageList<Department> findPage(DepartmentQuery query);

    /**
     * 上级部门的下拉框
     * @return 上级部门数据
     */
    List<Department> upDeptOptions();


    /**
     * 级联查询数据
     * @return 返回部门表数据
     */
    List<Department> getUpUp();



    /**
     * 新增/修改方法
     * @param department 一个对象
     */
    void saveOrUpdate(Department department);

}
