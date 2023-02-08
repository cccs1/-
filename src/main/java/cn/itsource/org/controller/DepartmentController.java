package cn.itsource.org.controller;

import cn.itsource.basic.uitl.JsonResult;
import cn.itsource.basic.uitl.PageList;
import cn.itsource.org.domain.Department;
import cn.itsource.org.query.DepartmentQuery;
import cn.itsource.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hello
 * @date 2023/2/4 15:05
 * 描述：
 */
@Api(tags={"部门接口"})
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @ApiOperation(value="查询所有部门", notes="详细描述")
    @GetMapping("/findAll")
    public JsonResult findAll(){
        List<Department> list = service.findAll();
        return JsonResult.success(list);
    }

    @ApiOperation(value="分页查询所有部门", notes="详细描述")
    @PostMapping("/findPage")
    public PageList<Department> findPage(@RequestBody DepartmentQuery query){
        PageList<Department> pageList = service.findPage(query);
        return pageList;
    }

    @ApiOperation(value="高级查询", notes="高级查询的上级部门下拉框")
    @GetMapping("/upOptions")
    public JsonResult upOptions(){
        List<Department> list = service.upDeptOptions();
        return JsonResult.success(list);
    }

    @ApiOperation(value="高级查询", notes="级联查询的下拉框")
    @GetMapping("/treeList")
    public JsonResult tree(){
        List<Department> list = service.getUpUp();
        return JsonResult.success(list);
    }

    @ApiOperation(value="部门表以主键id查询单个部门", notes="详细描述")
    @PostMapping("/{id}")
    public JsonResult findOne(@PathVariable("id") @ApiParam(value = "部门查询用户参数", required = true)Long id) {
        return JsonResult.success(service.findOne(id));
    }

    @ApiOperation(value="部门新增/修改用户", notes="详细描述")
    @PostMapping("/update")
    public JsonResult saveOrUpdate(@Valid @RequestBody @ApiParam(value = "新增/修改用户参数", required = true)Department department) {
        service.saveOrUpdate(department);
        return new JsonResult();
    }

    @ApiOperation(value="部门删除", notes="详细描述")
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id") @ApiParam(value = "部门删除用户参数", required = true)Long id) {
        try {
            service.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value="部门批量删除", notes="详细描述")
    @PostMapping("/batchRemove")
    public JsonResult deleteMuch(@RequestBody @ApiParam(value = "部门批量删除用户参数", required = true)DepartmentQuery departmentQuery) {
        try {
            service.deleteMuch(departmentQuery.getIds());
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }










}
