package cn.xuanma.test.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;
import java.util.concurrent.Future;

public interface IShardingService<T> {
    Integer queryCount(QueryWrapper<?> wrapper);

    Future<Integer> queryCountAsync(QueryWrapper<?> wrapper);

    List<T> queryList(QueryWrapper<?> wrapper, Integer pageNum, Integer pageSize);

    Future<List<T>> queryListAsync(QueryWrapper<?> wrapper, Integer pageNum, Integer pageSize);
}
