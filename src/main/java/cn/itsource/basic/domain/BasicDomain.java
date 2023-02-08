package cn.itsource.basic.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Hello
 * @date 2023/2/7 19:36
 * 描述：
 */
@Data
public class BasicDomain {

    @Id
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
