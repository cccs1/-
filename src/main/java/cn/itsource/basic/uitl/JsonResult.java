package cn.itsource.basic.uitl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hello
 * @date 2023/2/4 15:55
 * 描述：
 *      控制层返回信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private Boolean success = true;
    private String code = "0000";
    private String message = Constant.DEPT_OPERATION_SUCCESS;
    private Object data;

    public static JsonResult success(Object data, String msg) {
        return new JsonResult(true, Constant.DEPT_CODE, msg, data);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(true, Constant.DEPT_CODE, Constant.DEPT_OPERATION_SUCCESS, data);
    }

    public static JsonResult fail(String msg) {
        return new JsonResult(false, Constant.DEPT_CODE, msg, null);
    }

    public static JsonResult fail(Object data, String msg) {
        return new JsonResult(false, Constant.DEPT_FAIL_CODE, msg, data);
    }




}
