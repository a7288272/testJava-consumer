package cn.xuanma.test.controller;

import cn.xuanma.test.service.ITestNumService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import cn.xuanma.test.bean.entity.TestNum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuan
 * @since 2022-09-07
 */
@Controller
@RequestMapping("/testNum")
public class TestNumController {


    @Autowired
    private ITestNumService iTestNumService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<TestNum>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TestNum> aPage = iTestNumService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TestNum> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iTestNumService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TestNum params) {
        iTestNumService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iTestNumService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody TestNum params) {
        iTestNumService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
