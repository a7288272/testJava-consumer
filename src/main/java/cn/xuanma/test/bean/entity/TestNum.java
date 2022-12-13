package cn.xuanma.test.bean.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
/**
 * <p>
 *
 * </p>
 *
 * @author wangshuan
 * @since 2022-09-07
 */
@TableName("pre_num")
public class TestNum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "TestNum{" +
        ", id=" + id +
        ", num=" + num +
        "}";
    }
}
