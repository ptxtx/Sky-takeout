package com.sky.service;


import com.sky.dto.DishDTO;

public interface DishService {
    /*
    添加菜品 和口味
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
