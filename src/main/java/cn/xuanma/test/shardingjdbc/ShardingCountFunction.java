package cn.xuanma.test.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

@FunctionalInterface
public interface ShardingCountFunction<P1 extends Wrapper, R extends Integer> {
    R apply(P1 p1);
}

