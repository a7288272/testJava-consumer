package cn.xuanma.test.shardingjdbc;


import cn.xuanma.common.utils.DateUtils;
import cn.xuanma.common.utils.SpringContextUtil;
import cn.xuanma.test.config.shardingsphere.ShardingPropertics;
import cn.xuanma.test.utils.ShardingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

@Slf4j
public class DateYearPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        Date date = preciseShardingValue.getValue();
//        log.info("匹配分表时间：" + DateTimeUtil.dateToString(date));
        Date upperDate = new Date();
        Date lowerDate = DateUtils.parse("2000-01-01", "yyyy-MM-dd");
        ShardingPropertics shardingPropertics = SpringContextUtil.getBean(ShardingPropertics.class);
        if (shardingPropertics.getMinDate() != null) {
            lowerDate = shardingPropertics.getMinDate();
        }
        if (date.getTime() > upperDate.getTime()) {
            date = upperDate;
        }
        if (date.getTime() < lowerDate.getTime()) {
            date = lowerDate;
        }
        Integer suffix = ShardingUtils.getSuffixByYear(date);
//        log.info("匹配分表前缀：" + suffix);
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(suffix.toString())) {
                return tableName;
            }
        }
        throw new IllegalArgumentException("未找到匹配的数据表");
    }
}
