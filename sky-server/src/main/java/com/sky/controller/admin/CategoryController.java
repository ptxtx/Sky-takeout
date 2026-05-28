package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PutMapping
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("分类修改：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("分类状态：{}", status);
        log.info("分类id：{}", id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /*
    新增分类
     */
    @PostMapping
    public Result insert(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}", categoryDTO);
        categoryService.insert(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteById(Long id){
        log.info("删除分类：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        log.info("根据类型查询分类：{}", type);
       List<Category> listRes=categoryService.list(type);
        return Result.success(listRes);
    }//这里有List 是因为这里不像之前的根据唯一id查询 只有一条结果，这里可能有多条结果
}
