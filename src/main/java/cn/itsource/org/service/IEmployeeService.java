package cn.itsource.org.service;

import cn.itsource.basic.service.IBasicService;
import cn.itsource.basic.uitl.PageList;
import cn.itsource.org.domain.Employee;
import cn.itsource.org.query.EmployeeQuery;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/5 11:26
 * 描述：
 *      员工表
 */
public interface IEmployeeService extends IBasicService<Employee, EmployeeQuery> {

    /**
     * 管理员的下拉框
     * @return 管理员数据
     */
    List<Employee> manager();



}
