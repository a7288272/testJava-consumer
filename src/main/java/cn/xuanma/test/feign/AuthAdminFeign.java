package cn.xuanma.test.feign;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.test.feign.resp.AuthAdminRespDto;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "xm-auth", contextId = "authAdminFeign")
public interface AuthAdminFeign {
    final String URL = "/auth-admin";

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage();

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage(@RequestParam(value = "username", required = false) String username);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "userphone", required = false) String userphone);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    @GetMapping(URL + "/findPage")
    RetResult<PageInfo<AuthAdminRespDto>> findPage(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "userphone", required = false) String userphone,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @GetMapping(URL + "/detail")
    RetResult<AuthAdminRespDto> detail(@RequestParam("id") Integer id);

    /**
     * 获取用户权限
     * @param appCode
     * @param userId
     * @return
     */
    @GetMapping(URL + "/getMenuPermissionList")
    RetResult<List<String>> getMenuPermissionList(@RequestParam(value = "appCode") String appCode,
                                                  @RequestParam(value = "userId") Integer userId);

    /**
     * 获取用户数据权限
     *
     * @param dataCode
     * @param appCode
     * @param userId
     * @return
     */
    @GetMapping(URL + "/getDataPermissionList")
    RetResult<List<String>> getDataPermissionList(@RequestParam(value = "dataCode") String dataCode,
                                                  @RequestParam(value = "appCode") String appCode,
                                                  @RequestParam(value = "userId") Integer userId);
}
