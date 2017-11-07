package com.example;

import java.util.ArrayList;

/**
 * Created by DongJunJie on 2017-11-2.
 *
 *
 * 这个方法比较耗时，但是却是必然不会有错误的一种算法
 *
 */

public class T2 {


    static int max = 0;
    static int[][] map;

    public static void old(int[][] theMap) {
        max = 0;
        map = theMap;
        ArrayList<Integer> U = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            U.add(i);
        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        clique(U, 0, ans);
        println("MAX" + max + "end");
    }

    public static void clique(ArrayList<Integer> U, int size, ArrayList<Integer> ans) {
        if (U.size() == 0) {
            if (size > max) {
                max = size;
                println("MAX" + max + "   " + ans);
            }
        }
        while (U.size() != 0) {
            if (size + U.size() < max) {
                return;
            }
            int i = U.get(0);
            U.remove(0);
            ArrayList<Integer> u2 = getClip(U, i);
            ans.add(i);
            clique(u2, size + 1, ans);
        }
    }

    public static ArrayList<Integer> getClip(ArrayList<Integer> U, int point) {
        int len = U.size();
        ArrayList<Integer> rt = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int v = U.get(i);
            if (map[v][point] == 1) {
                rt.add(v);
            }
        }
        return rt;
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.print(s + "\n");
    }
}
