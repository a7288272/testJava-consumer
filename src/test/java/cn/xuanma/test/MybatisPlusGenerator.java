package cn.xuanma.test;


import cn.hutool.core.util.StrUtil;
import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;
import com.google.common.base.Strings;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        String suffix = "Auth";
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:mysql://119.23.204.229:3306/xm_auth?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai")
                .userName("root")
                .password("Xmtest@*888999")
                .port(8068)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .basePackage("cn.xuanma.test")
                .nameConverter(new NameConverter() {
                    @Override
                    public String entityNameConvert(String tableName) {
                        if (Strings.isNullOrEmpty(tableName)) {
                            return "";
                        } else {
                            tableName = tableName.substring(tableName.indexOf("_") + 1);
                            return suffix + StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase()));
                        }
                    }
                })
                .build();
        MybatisPlusToolsApplication.run(config);
    }
}
