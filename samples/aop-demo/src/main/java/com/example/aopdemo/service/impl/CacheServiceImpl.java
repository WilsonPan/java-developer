package com.example.aopdemo.service.impl;

import org.springframework.stereotype.Service;

import com.example.aopdemo.aspect.CacheResult;
import com.example.aopdemo.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @CacheResult
    public String getById(String id) {
        return "Wilson Pan" + id;
    }

    /**
     * 二分搜索算法实现
     * 在有序数组中查找目标值
     * 
     * @param nums 有序整数数组（升序）
     * @param target 要查找的目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    protected int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        // 修复：循环条件应该是 left <= right，而不是 left < right
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
