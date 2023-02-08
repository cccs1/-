package cn.itsource.org.mapper;

import cn.itsource.org.domain.Department;
import cn.itsource.org.domain.Employee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/5 11:24
 * 描述：
 */
public interface EmployeeMapper extends Mapper<Employee> {

    /**
     * 管理员的下拉框
     * @return 管理员id和数据
     */
    List<Employee> getManager();

    /**
     * 所有员工
     * @return 返回所有员工和id
     */
    List<Employee> getAllEmp();

}
