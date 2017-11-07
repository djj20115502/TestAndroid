package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DongJunJie on 2017-11-2.
 */

public class Change {

    //        static ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(5);
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();


    public static void bigmap(int[][] map) {
        println("====================开始=================");
        //mainStep1:获得最大的临时团
//        printlnMap(map, "map G");
        ArrayList<Integer> cbList = getMaxT(map);
        println(" *******************交换*************** ");
        //mainStep2:进行资源交换
        change(map, cbList);
        println("====================输出结果============= 点个数" + cbList.size());
        println(cbList);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        save(formatter.format(System.currentTimeMillis()) + "输出结果:点个数" + cbList.size() + "\n" +
                cbList + "\n", "out.txt");
    }


    /**
     * 计算第一步的最大团
     */
    public static ArrayList<Integer> getMaxT(int[][] mapG) {
        int[] dArray;
        int topPoint;
        ArrayList<Integer> cbList = new ArrayList<>();
        //初始化时的满足要求的点集合，由于第一次没有基准点，所以全部合格
        ArrayList<Integer> cadGroup = new ArrayList<>();
        for (int i = 0; i < mapG.length; i++) {
            cadGroup.add(i);
        }
        //MaxTStep1：这里在进行算法前先取出那些必然符合的点，即度数为N-1的
        dArray = getDegrees(mapG, cadGroup);
        for (int i = 0; i < cadGroup.size(); i++) {
            if (dArray[i] == cadGroup.size() - 1) {
                println(i + "度数为N-1直接加入");
                cbList.add(i);
            }
        }
        cadGroup.removeAll(cbList);
        //MaxTStep2：循环取空cadGroup
        while (cadGroup.size() != 0) {
            //获得cadGroup中点的度数排列，
            dArray = getDegrees(mapG, cadGroup);
            printlnArray(dArray, "度数：  ");
            //获得最大度数的点
            topPoint = getTopDegrees(dArray);
            if (topPoint < 0) {
                break;
            }
            //添加到最大团中
            cbList.add(topPoint);
            //节选出临边节点集合
            cadGroup = getCad(topPoint, mapG, cadGroup);
            println("Cb:" + cbList);
            println("Cad:" + cadGroup);
            println("-----------------------------------------------------");
        }
        return cbList;
    }


    public static ArrayList<Integer> change(int[][] map, ArrayList<Integer> cbList) {
        //changeStep1：边界判定-如果最开始的临时团都没找到，那么肯定后续也找不到
        if (cbList == null || cbList.size() < 2) {
            println("没意义不用交换");
            return cbList;
        }
        ArrayList<Integer> reserves = new ArrayList<>();
        int len = cbList.size();
        int[] allDegrees = getDegrees(map, null);
        for (int i = 0; i < map.length; i++) {
            if (!cbList.contains(i) && allDegrees[i] >= len) {
                reserves.add(i);
            }
        }
        //changeStep2：边界判定-如果剩下的点的数量少于2个，肯定不能交换
        if (reserves.size() < 2) {
            println("change  reserves.size() < 2退出");
            return cbList;
        }
        //changeStep3：轮询是否可以交换
        for (int i = 0; i < cbList.size(); i++) {
            int point = cbList.get(i);
            ArrayList<Integer> canChanges = findChangePoint(point, map, cbList, reserves);
            if (canChanges != null) {
                println("change  canChanges:" + canChanges);
                cbList.remove(new Integer(point));
                i = 0;//这里由于考虑到交换后对前面 可换点为1的情况会影响所以得再循环
                cbList.addAll(canChanges);
                reserves.removeAll(canChanges);
                removeMap(map, cbList, reserves);
                if (reserves.size() < 2) {
                    println("change  reserves.size() < 2退出");
                    return cbList;
                }
            }
        }
        return cbList;
    }


    public static int[][] removeMap(int[][] map, ArrayList<Integer> cbList, ArrayList<Integer>
            reserves) {
        int len = map.length;
        int size = cbList.size() - 1;
        for (int i = 0; i < len; i++) {
            int degree = getD(map[i]);
            if (cbList.contains(i)) {
                continue;
            }
            if (degree <= size && degree > 0) {
                println("------------点 " + i + "度数" + degree + "小于等于cbList.size-1：" + size + "  " +
                        "删除-----------------------");
                if (reserves.contains(i)) {
                    reserves.remove(new Integer(i));
                }
                for (int j = 0; j < len; j++) {
                    map[i][j] = 0;
                    map[j][i] = 0;
                }
                i = -1;
            }
        }
        return map;
    }

    public static int getD(int[] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            count += map[i];
        }
        return count;
    }

    /**
     * 寻找可交换的点
     *
     * @param point    换出点。
     * @param cbList   临时最大团的点集合
     * @param reserves 剩余的候选点集合
     * @return 可交换的点集  返回size必然大于等于2，否则为空
     */
    public static ArrayList<Integer> findChangePoint(int point, int[][] map,
                                                     ArrayList<Integer> cbList,
                                                     ArrayList<Integer> reserves) {
//        println("findChangePoint  point:" + point + " cbList:" + cbList.size() + ":" + cbList +
// "" +
//                " reserves:"
//                + reserves);
        ArrayList<Integer> ok = new ArrayList<>();
        //要求1：寻找满足要求的点的集合 放入ok中。被选点与cbList中除了换出点外的度数和必须为N-1
        for (Integer integer1 : reserves) {
            if (map[integer1][point] == 1) {
                continue;
            }
            int count = 0;
            for (Integer integer2 : cbList) {
                if (integer2 == point) {
                    continue;
                }
                if (map[integer1][integer2] == 1) {
                    count++;
                }
            }
//            println("findChangePoint  count:" + count + "  cbList.length:" + cbList.size() + "
// " +
//                    "reserves[integer1]" + integer1);
            if (count == cbList.size() - 1) {
                ok.add(integer1);
            }
        }
//        println("ok：" + ok);
        int[][] map2 = catMap(map, ok);
        //要求2：这些符合要求的候选点之间也必须要组成团。由于这里不需要一步到位，且只需要点个数大于2即可
        //只是一个临时最大团也没问题.所以这里直接用getMaxT方法
        ArrayList<Integer> maxT = getMaxT(map2);
//        println("findChangePoint  point:" + point + "  maxT:" + maxT);
        if (maxT == null) {
            return null;
        }
//        if (maxT.size() < 2) {
//            return null;
//        }
        ArrayList<Integer> rt2 = new ArrayList<>();
        for (int i = 0; i < maxT.size(); i++) {
            rt2.add(ok.get(maxT.get(i)));
        }
        return rt2;
    }


    /**
     * 获得子图
     */
    public static int[][] catMap(int[][] map, ArrayList<Integer> cadGroup) {
        if (cadGroup == null) {
            return null;
        }
        if (cadGroup == null) {
            return null;
        }
        int len = cadGroup.size();
        if (len == 0) {
            return null;
        }
        int[][] rt = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                rt[i][j] = map[i][j];
            }
        }
        return rt;
    }


    /**
     * 获得度数列表
     * 在图中点集的度数
     *
     * @param map      全图
     * @param cadGroup 点集,只计算这几个点的度数,其他点省略
     */
    public static int[] getDegrees(int[][] map, ArrayList<Integer> cadGroup) {
        int len = map.length;
        int[] rt = new int[len];
        for (int i = 0; i < len; i++) {
            if (cadGroup != null && !cadGroup.contains(i)) {
                continue;
            }
            int d = 0;
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                if (map[i][j] == 1) {
                    d++;
                }
                rt[i] = d;
            }
        }
        return rt;
    }


    /**
     * 获得度数列表
     */
    public static int getTopDegrees(int[] dArray) {
        if (dArray == null) {
            println("getTopDegrees dArray=null");
            return -1;
        }
        int len = dArray.length;
        int top = 0;
        int point = -1;
        for (int i = 0; i < len; i++) {
            if (dArray[i] > top) {
                point = i;
                top = dArray[i];
            }
        }
        return point;
    }

    /**
     * 获得Cad列表  就是最高度数的临边列表
     */
    public static ArrayList<Integer> getCad(int top, int[][] map, ArrayList<Integer>
            cadGroup) {
        ArrayList<Integer> rt = new ArrayList<>();
        for (Integer integer : cadGroup) {
            if (integer == top) {
                continue;
            }
            if (map[top][integer] == 1) {
                rt.add(integer);
            }
        }
        return rt;
    }


    public static void printlnArray(int[] array, String title) {
        if (title == null) {
            title = "null";
        }
        if (array == null) {
            println("printlnArray array=null");
            return;
        }
        int len = array.length;
        if (len <= 0) {
            println("printlnArray array.size()=0");
        }
        println(title);
        for (int i = 0; i < len; i++) {
            print(" " + array[i]);
        }
        println(" ");
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static int[][] readFileByLines(String fileName) {
        int[][] rt = null;
        File file = new File(fileName);
        BufferedReader reader = null;
        FileReader fileReader = null;
        int len = 0;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                tempString = tempString.replaceAll("\\{", "");
                tempString = tempString.replaceAll("\\}", "");
                tempString = tempString.replaceAll(" ", "");
                String[] sa = tempString.split(",");
                if (rt == null) {
                    len = sa.length;
                    rt = new int[len][len];
                }
                if (line > len) {
                    println("行数" + line + "大于列数 不是方阵");
                    return null;
                }
                if (sa.length != len) {
                    println("第" + line + "行的个数不等 不是方阵");
                    return null;
                }
                for (int i = 0; i < len; i++) {
                    int va = getInt(sa[i]);
                    if (va != 0 && va != 1) {
                        println("第" + line + "行" + i + "列的值" + sa[i] + "不为1或0");
                        return null;
                    }
                    rt[line][i] = va;
                }
                line++;
            }
            reader.close();
            if (line == len) {
                for (int i = 0; i < len; i++) {
                    rt[i][i] = 0;
                }
                return rt;
            } else {
                println("行数" + line + "列数" + len + "不等");
            }
        } catch (IOException e) {
            println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    println(e1.getMessage());
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    println(e.getMessage());
                }
            }
        }
        return null;
    }

    public static int getInt(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.valueOf(s);
        } catch (Exception e) {
            return -1;
        }
    }


    public static void println(Object var1) {
        String var2 = String.valueOf(var1);
        print(var2 + "\n");
    }

    public static void print(final Object var1) {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String var2 = String.valueOf(var1);
//                System.out.print(var2);
//                save(var2, "log.txt");
            }
        });
    }

    public static void save(String inputText, String fileName) {
        FileWriter fw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out = new PrintWriter(fw);
        if (out != null) {
            out.print(inputText);
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}