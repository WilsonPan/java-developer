package com.example.aopdemo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * CacheServiceImpl 二分搜索方法单元测试
 */
class CacheServiceImplTest {

    private CacheServiceImpl cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheServiceImpl();
    }

    @Test
    @DisplayName("测试：在数组中间找到目标值")
    void testSearch_TargetInMiddle() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 5);
        assertEquals(2, result, "应该在索引2处找到值5");
    }

    @Test
    @DisplayName("测试：在数组开头找到目标值")
    void testSearch_TargetAtBeginning() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 1);
        assertEquals(0, result, "应该在索引0处找到值1");
    }

    @Test
    @DisplayName("测试：在数组末尾找到目标值")
    void testSearch_TargetAtEnd() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 9);
        assertEquals(4, result, "应该在索引4处找到值9");
    }

    @Test
    @DisplayName("测试：目标值不存在（小于最小值）")
    void testSearch_TargetNotFound_LessThanMin() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 0);
        assertEquals(-1, result, "值0不存在，应返回-1");
    }

    @Test
    @DisplayName("测试：目标值不存在（大于最大值）")
    void testSearch_TargetNotFound_GreaterThanMax() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 10);
        assertEquals(-1, result, "值10不存在，应返回-1");
    }

    @Test
    @DisplayName("测试：目标值不存在（在范围内）")
    void testSearch_TargetNotFound_InRange() {
        int[] nums = {1, 3, 5, 7, 9};
        int result = cacheService.search(nums, 6);
        assertEquals(-1, result, "值6不存在，应返回-1");
    }

    @Test
    @DisplayName("测试：单元素数组找到目标值")
    void testSearch_SingleElement_Found() {
        int[] nums = {5};
        int result = cacheService.search(nums, 5);
        assertEquals(0, result, "应该在索引0处找到值5");
    }

    @Test
    @DisplayName("测试：单元素数组未找到目标值")
    void testSearch_SingleElement_NotFound() {
        int[] nums = {5};
        int result = cacheService.search(nums, 3);
        assertEquals(-1, result, "值3不存在，应返回-1");
    }

    @Test
    @DisplayName("测试：双元素数组找到第一个元素")
    void testSearch_TwoElements_FirstElement() {
        int[] nums = {3, 7};
        int result = cacheService.search(nums, 3);
        assertEquals(0, result, "应该在索引0处找到值3");
    }

    @Test
    @DisplayName("测试：双元素数组找到第二个元素")
    void testSearch_TwoElements_SecondElement() {
        int[] nums = {3, 7};
        int result = cacheService.search(nums, 7);
        assertEquals(1, result, "应该在索引1处找到值7");
    }

    @Test
    @DisplayName("测试：空数组")
    void testSearch_EmptyArray() {
        int[] nums = {};
        int result = cacheService.search(nums, 5);
        assertEquals(-1, result, "空数组应返回-1");
    }

    @Test
    @DisplayName("测试：null数组")
    void testSearch_NullArray() {
        int result = cacheService.search(null, 5);
        assertEquals(-1, result, "null数组应返回-1");
    }

    @Test
    @DisplayName("测试：包含重复元素的数组")
    void testSearch_DuplicateElements() {
        int[] nums = {1, 3, 5, 5, 5, 7, 9};
        int result = cacheService.search(nums, 5);
        assertTrue(result >= 2 && result <= 4, "应该找到值5的某个索引位置");
        assertEquals(5, nums[result], "找到的位置应该包含值5");
    }

    @Test
    @DisplayName("测试：较大的数组")
    void testSearch_LargeArray() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = i * 2;
        }
        
        // 测试找到目标值
        int result = cacheService.search(nums, 500);
        assertEquals(250, result, "应该在索引250处找到值500");
        
        // 测试未找到目标值（奇数）
        int notFoundResult = cacheService.search(nums, 501);
        assertEquals(-1, notFoundResult, "值501不存在，应返回-1");
    }

    @Test
    @DisplayName("测试：负数数组")
    void testSearch_NegativeNumbers() {
        int[] nums = {-9, -7, -5, -3, -1};
        int result = cacheService.search(nums, -5);
        assertEquals(2, result, "应该在索引2处找到值-5");
    }

    @Test
    @DisplayName("测试：包含负数和正数的数组")
    void testSearch_MixedNumbers() {
        int[] nums = {-5, -3, -1, 0, 2, 4, 6};
        
        int result1 = cacheService.search(nums, -3);
        assertEquals(1, result1, "应该在索引1处找到值-3");
        
        int result2 = cacheService.search(nums, 0);
        assertEquals(3, result2, "应该在索引3处找到值0");
        
        int result3 = cacheService.search(nums, 4);
        assertEquals(5, result3, "应该在索引5处找到值4");
    }
}
