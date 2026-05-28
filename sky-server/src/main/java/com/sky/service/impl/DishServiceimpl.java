package com.sky.service.impl;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DishServiceimpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /*
    * 新增菜品及口味
     */
    @Override
    @Transactional
    @AutoFill(OperationType.INSERT)
    public void saveWithFlavor(DishDTO dishDTO) {
        //向菜品表插入数据
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);

        //向口味表插入n条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            //给菜品口味设置菜品id
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());//注意要在xml标签中配置 useGeneratedKeys="true"&keyProperty="id"
            });

            dishFlavorMapper.insertBatch(flavors);
        }

    }

}
