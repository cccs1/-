package cn.itsource.org.service.impl;

import cn.itsource.basic.service.impl.BasicImpl;
import cn.itsource.org.domain.Employee;
import cn.itsource.org.mapper.EmployeeMapper;
import cn.itsource.org.query.EmployeeQuery;
import cn.itsource.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/5 11:39
 * 描述：
 */
@Service
public class EmployeeServiceImpl extends BasicImpl<Employee, EmployeeQuery> implements IEmployeeService {

    @Autowired
    private EmployeeMapper mapper;


    @Override
    public List<Employee> manager() {
        return mapper.getManager();
    }

    @Override
    public List<Employee> findAll() {
        return mapper.getAllEmp();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            mapper.insertSelective(employee);
        }else {
            mapper.updateByPrimaryKeySelective(employee);
        }
    }


}
