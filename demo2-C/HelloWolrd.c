#include <stdio.h>

int removeDuplicates(int *nums, int numsSize) {

    if (numsSize == 0) {
        return 0;
    }

    int slow = 0;
    int fast = 1;
    int removeCount = 0;
    for (int i = 0; fast < numsSize; ++i) {
        if (nums[slow] == nums[fast]) {
            removeCount++;
        } else {
            nums[slow + 1] = nums[fast];
            slow++;
        }
        fast++;
    }

    return numsSize - removeCount;
}

int main() {
    // printf() displays the string inside quotation
    printf("Hello, World!");
    return 0;
}