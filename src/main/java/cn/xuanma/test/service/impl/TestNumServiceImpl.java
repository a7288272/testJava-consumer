package cn.xuanma.test.service.impl;

import cn.xuanma.test.mapper.TestNumMapper;
import cn.xuanma.test.service.ITestNumService;
import cn.xuanma.test.bean.entity.TestNum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuan
 * @since 2022-09-07
 */
@Service
public class TestNumServiceImpl extends ServiceImpl<TestNumMapper, TestNum> implements ITestNumService {

}
