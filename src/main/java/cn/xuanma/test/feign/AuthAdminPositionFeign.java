package cn.xuanma.test.feign;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.test.feign.resp.AuthAdminPositionRespDto;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xm-auth", contextId = "authAdminPositionFeign")
public interface AuthAdminPositionFeign {
    final String URL = "/auth-admin-position";

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminPositionRespDto>> findPage();

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminPositionRespDto>> findPage(@RequestParam(value = "name", required = false) String name);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminPositionRespDto>> findPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminPositionRespDto>> findPage(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @GetMapping(URL + "/detail")
    RetResult<AuthAdminPositionRespDto> detail(@RequestParam("id") Integer id);
}
