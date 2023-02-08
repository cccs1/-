package cn.itsource.org.controller;

import cn.itsource.basic.uitl.JsonResult;
import cn.itsource.org.domain.Employee;
import cn.itsource.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hello
 * @date 2023/2/5 21:42
 * 描述：
 */
@Api(tags={"员工接口"})
@RestController
@RequestMapping("/user")
public class EmployeesController {
    @Autowired
    private IEmployeeService service;


    @ApiOperation(value="高级查询", notes="高级查询的管理员下拉框")
    @GetMapping("/manager")
    public JsonResult manager(){
        List<Employee> list = service.manager();
        return JsonResult.success(list);
    }

    @ApiOperation(value = "高级查询新增数据", notes = "高级查询的所有员工的下拉框")
    @GetMapping("/emp")
    public JsonResult emp() {
        List<Employee> list = service.findAll();
        return JsonResult.success(list);
    }



}
