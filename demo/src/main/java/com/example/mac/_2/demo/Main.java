package com.example.mac._2.demo;

import java.util.Arrays;

public class Main {

    public static class Solution {

        public int maxWidthOfVerticalArea(int[][] points) {

            int[] xPoints = Arrays.stream(points)
                    .mapToInt(point -> point[0])
                    .toArray();
            Arrays.sort(xPoints);

            int result = Integer.MIN_VALUE;
            for (int i = 1; i < xPoints.length; i++) {
                result = Math.max(result, xPoints[i] - xPoints[i - 1]);
            }

            return result;
        }
    }

}
