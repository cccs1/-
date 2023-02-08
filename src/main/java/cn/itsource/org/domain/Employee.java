package cn.itsource.org.domain;


import cn.itsource.basic.domain.BasicDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Hello
 *     员工表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_employee")
@ApiModel(value = "cn.itsource.org.domain.Employee", description = "员工表")
public class Employee extends BasicDomain {
    /**
     *  id
     *  username    登录帐号,
     *  email   邮箱,
     *  phone   手机号,
     *  password    登录密码,
     *  age     年龄,
     *  state   1待审核；2审核通过；3激活成功；4审核驳回（关闭）,
     *  department_id   关联的部门ID,
     *  logininfo_id    关联的登录信息ID,
     *  shop_id     关联的店铺ID,
     *  parent_id       员工的上级ID,
     *  manager_id      jikluull员工的上级经理ID,
     */

    @ApiModelProperty(value = "登录帐号")
    private String username;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "年龄")
    private Long age;
    @ApiModelProperty(value = "1待审核；2审核通过；3激活成功；4审核驳回（关闭）,")
    private Long state;
    @ApiModelProperty(value = "关联的部门ID")
    @Column(name = "department_id")
    private Long departmentId;
    @ApiModelProperty(value = "关联的登录信息ID")
    private Long logininfoId;
    @ApiModelProperty(value = "关联的店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "员工的上级ID")
    private Long parentId;
    @ApiModelProperty(value = "jikluull员工的上级经理ID")
    private Long managerId;

}
