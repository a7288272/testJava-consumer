package cn.xuanma.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import cn.xuanma.test.bean.entity.AuthApp;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author wangshuan
 * @since 2022-09-20
 */
public interface AuthAppMapper extends BaseMapper<AuthApp> {

    @Update("update auth_app set is_deleted = null where id = #{id}")
    Integer delete(Integer id);
}
