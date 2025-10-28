public class ArrayDemo {
    
    /**
     * 原始有问题的代码
     * 问题：循环条件 i <= nums.length 会导致 ArrayIndexOutOfBoundsException
     * 因为数组索引是从 0 到 nums.length - 1
     */
    private static void eachResultWithBug(String[] nums) {
        System.out.println("=== 有问题的代码执行 ===");
        try {
            for (int i = 0; i <= nums.length; i++) {
                String str = nums[i];
                System.out.println(str);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到异常: " + e.getMessage());
        }
    }
    
    /**
     * 修复后的代码
     * 正确的循环条件应该是 i < nums.length
     */
    private static void eachResultFixed(String[] nums) {
        System.out.println("=== 修复后的代码执行 ===");
        for (int i = 0; i < nums.length; i++) {
            String str = nums[i];
            System.out.println(str);
        }
    }
    
    /**
     * 使用增强for循环的版本（推荐）
     */
    private static void eachResultEnhanced(String[] nums) {
        System.out.println("=== 增强for循环版本 ===");
        for (String str : nums) {
            System.out.println(str);
        }
    }
    
    public static void main(String[] args) {
        String[] testArray = {"apple", "banana", "cherry"};
        
        // 演示有问题的代码
        eachResultWithBug(testArray);
        System.out.println();
        
        // 演示修复后的代码
        eachResultFixed(testArray);
        System.out.println();
        
        // 演示增强for循环版本
        eachResultEnhanced(testArray);
    }
}
