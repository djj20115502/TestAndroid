package com.example;

import java.util.ArrayList;

/**
 * Created by DongJunJie on 2017-11-3.
 * <p>
 * 尝试动态规划
 */

public class D {
    public static void dd(int[][] map) {
        int len = map.length;
        ArrayList<Integer> add;
        ArrayList<ArrayList<Integer>> theRoot = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (map[i][j] == 1) {
                    ArrayList<Integer> the2One = new ArrayList<>();
                    the2One.add(i);
                    the2One.add(j);
                    theRoot.add(the2One);
                }
            }
        }
        System.out.println("子图" + theRoot.get(0).size() + "  " + theRoot.size());
        while (theRoot != null && theRoot.size() > 1) {
            theRoot = add(theRoot, map);
            System.out.println("子图" + theRoot.get(0).size() + "  " + theRoot.size());
        }
        System.out.println("子图" + theRoot);
    }

    public static ArrayList<ArrayList<Integer>> add(ArrayList<ArrayList<Integer>> base, int[][]
            map) {
        int len = base.size();
        ArrayList<Integer> add;
        ArrayList<ArrayList<Integer>> rt = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                add = checkAndDoAdd(base.get(i), base.get(j), map);
                if (add != null) {
                    rt.add(add);
                }
            }
        }
        return rt;
    }


    /**
     * 实现检测是否可以合并 并执行合并
     *
     * @param b1  左完全子图
     * @param b2  右完全子图
     * @param map 点图
     * @return 合并后的结果，如果不能合并，返回空
     */
    public static ArrayList<Integer> checkAndDoAdd(ArrayList<Integer> b1, ArrayList<Integer> b2,
                                                   int[][] map) {
        if (b1.size() != b2.size()) {
            return null;
        }
        ArrayList<Integer> rt = new ArrayList<>();
        int point;
        int len = b1.size();
        ArrayList<Integer> b11 = (ArrayList<Integer>) b1.clone();
        ArrayList<Integer> b22 = (ArrayList<Integer>) b2.clone();
        for (int i = 0; i < b11.size(); i++) {
            point = b11.get(i);
            if (b22.contains(point)) {
                rt.add(point);
                b11.remove(new Integer(point));
                b22.remove(new Integer(point));
                i--;
            }
        }
        if (rt.size() != len - 1) {
            return null;
        }
        int b1Residue = b11.get(0);
        int b2Residue = b22.get(0);
        if (map[b1Residue][b2Residue] != 1) {
            return null;
        }
        rt.add(b1Residue);
        rt.add(b2Residue);
        //这一步是去重
        int len2 = rt.size();
        for (int i = 0; i < len2 - 1; i++) {
            if (rt.get(i) > rt.get(i + 1)) {
                return null;
            }
        }
        return rt;
    }

}
