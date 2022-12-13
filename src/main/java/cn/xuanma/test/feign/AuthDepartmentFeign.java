package cn.xuanma.test.feign;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.test.feign.resp.AuthDepartmentRespDto;
import cn.xuanma.test.feign.resp.AuthDepartmentTreeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "xm-auth", contextId = "authDepartmentFeign")
public interface AuthDepartmentFeign {
    final String URL = "/auth-department";

    /**
     * 获取部门列表
     *
     * @return
     */
    @GetMapping(URL + "/findTree")
    RetResult<List<AuthDepartmentTreeDto>> findTree();

    @GetMapping(URL + "/findTree")
    RetResult<List<AuthDepartmentTreeDto>> findTree(@RequestParam(value = "name", required = false) String name);

    @GetMapping(URL + "/findTree")
    RetResult<List<AuthDepartmentTreeDto>> findTree(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "code", required = false) String code);

    @GetMapping(URL + "/findTree")
    RetResult<List<AuthDepartmentTreeDto>> findTree(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "code", required = false) String code,
                                                    @RequestParam(value = "parentId", required = false) Integer parentId);

    /**
     * 获取部门详情
     *
     * @param id
     * @return
     */
    @GetMapping(URL + "/detail")
    RetResult<AuthDepartmentRespDto> detail(@RequestParam("id") Integer id);
}
