package cn.xuanma.test.config.mybatisplus;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.SneakyThrows;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlTableRuleConfiguration;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
), @Signature(
        type = StatementHandler.class,
        method = "parameterize",
        args = {Statement.class}
), @Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class MybatisPlusInterceptor extends com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor {
    private final YamlShardingRuleConfiguration yamlShardingRuleConfiguration;
    private Map<String, YamlTableRuleConfiguration> shardTableMap;

    public MybatisPlusInterceptor(YamlShardingRuleConfiguration yamlShardingRuleConfiguration) {
        this.yamlShardingRuleConfiguration = yamlShardingRuleConfiguration;
        getShardingTables();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String method = invocation.getMethod().getName();
        if (method.equals("parameterize")) {
            return parameterize(invocation);
        }
        return super.intercept(invocation);
    }

    @SneakyThrows
    private Object parameterize(Invocation invocation) {
        BoundSql boundSql = getBoundSql(invocation);
        MappedStatement mappedStatement = getMappedStatement(invocation);
        // ???????????????????????????
        if (SqlCommandType.UPDATE != mappedStatement.getSqlCommandType()) {
            return invocation.proceed();
        }
        // ????????????????????????????????????????????????????????????,????????????????????????????????????
        Class<?> pojoClazz = mappedStatement.getParameterMap().getType();
        if (pojoClazz == null || !pojoClazz.isAnnotationPresent(TableName.class)) {
            return invocation.proceed();
        }
        TableName annotation = pojoClazz.getAnnotation(TableName.class);
        if (shardTableMap.containsKey(annotation.value())) {
            YamlTableRuleConfiguration shardConfig = shardTableMap.get(annotation.value());
            String shardingColumn = null;
            if (shardConfig.getTableStrategy().getStandard() != null) {
                shardingColumn = shardConfig.getTableStrategy().getStandard().getShardingColumn();
            }
            if (shardConfig.getTableStrategy().getInline() != null) {
                shardingColumn = shardConfig.getTableStrategy().getInline().getShardingColumn();
            }
            if (shardingColumn != null) {
                // ???????????????????????????,?????????????????????,???????????????????????????????????????????????????
                String camelCase = StrUtil.toCamelCase(shardingColumn);
                // ????????????????????????
                ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), "et." + camelCase, Object.class).build();
                boundSql.getParameterMappings().add(parameterMapping);
            }
        }
        return invocation.proceed();
    }

    private MappedStatement getMappedStatement(Invocation invocation) throws NoSuchFieldException, IllegalAccessException {
        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
        ParameterHandler parameterHandler = routingStatementHandler.getParameterHandler();
        // ??????????????????BoundSql,MappedStatement??????
        Field mappedStatementField = parameterHandler.getClass().getDeclaredField("mappedStatement");
        mappedStatementField.setAccessible(true);
        return (MappedStatement) mappedStatementField.get(parameterHandler);
    }

    private BoundSql getBoundSql(Invocation invocation) {
        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
        return routingStatementHandler.getBoundSql();
    }

    private void getShardingTables() {
        if (null != shardTableMap) {
            return;
        }
        synchronized (MybatisPlusInterceptor.class) {
            if (null != shardTableMap) {
                return;
            }
            if (null == yamlShardingRuleConfiguration) {
                shardTableMap = Collections.emptyMap();
                return;
            }
            shardTableMap = yamlShardingRuleConfiguration.getTables();
        }
    }
}
