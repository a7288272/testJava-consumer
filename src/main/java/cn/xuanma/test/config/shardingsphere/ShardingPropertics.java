package cn.xuanma.test.config.shardingsphere;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Configuration
@ConfigurationProperties(prefix = "xm.shardingsphere")
@Data
public class ShardingPropertics {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minDate;
}
