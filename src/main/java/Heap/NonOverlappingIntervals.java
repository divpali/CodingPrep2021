package Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    /**
     * Actually, the problem is the same as "Given a collection of intervals, find the maximum number
     * of intervals that are non-overlapping." (the classic Greedy problem: Interval Scheduling).
     * With the solution to that problem, guess how do we get the minimum number of intervals to remove? : )
     *
     * Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get the
     * maximum number of non-overlapping intervals is O(n). Total is O(nlogn)
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0)  return 0;

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start- o2.start;
            }
        });

        Interval earlier_meeting = intervals[0];
        int count = 1;

        for (int i = 1; i < intervals.length; i++) {
            Interval current_meeting = intervals[i];
            if (current_meeting.start >= earlier_meeting.end) {
                earlier_meeting.end=current_meeting.end;
                count++;
            }
        }

        return intervals.length - count;

//        int end = intervals[0].end;
//        int count = 1;
//
//        for (int i = 1; i < intervals.length; i++) {
//            if (intervals[i].start >= end) {
//                end = intervals[i].end;
//                count++;
//            }
//        }
//        return intervals.length - count;
    }

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(1,2), new Interval(2,3), new Interval(3,4), new Interval(1,3)};

        NonOverlappingIntervals n = new NonOverlappingIntervals();
        System.out.println(n.eraseOverlapIntervals(intervals));
    }
}
