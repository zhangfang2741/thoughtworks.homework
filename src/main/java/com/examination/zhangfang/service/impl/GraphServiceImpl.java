package com.examination.zhangfang.service.impl;

import com.examination.zhangfang.constans.DirectGraphConstans;
import com.examination.zhangfang.service.GraphService;

import java.util.ArrayList;
import java.util.List;

import static com.examination.zhangfang.constans.DirectGraphConstans.ORIGINAL_NODE;

public class GraphServiceImpl implements GraphService {
    private List<String> routes;
    private int[][] map;

    /**
     * 初始化图
     **/
    public GraphServiceImpl(int[][] map) {
        this.map = map;
    }

    /**
     * 获取指定路线的距离
     *
     * @param route 指定路线 如：{'A','B','C'}
     * @return 0表示不可达.
     */
    @Override
    public int getDistance(char[] route) {
        int distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            int vetrixDistance = getDistance(route[i] - ORIGINAL_NODE, route[i + 1] - ORIGINAL_NODE);
            if (vetrixDistance == 0)
                return 0;
            distance = distance + vetrixDistance;
        }
        return distance;
    }


    /**
     * 构建路线
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return 符合条件的线路集合
     */
    @Override
    public List<String> buildRoutes(char start, char end, int stops, int maxDistance) {
        routes = new ArrayList<>();
        dfs(start - ORIGINAL_NODE, end, String.valueOf(start), 0, stops, maxDistance);
        return routes;
    }

    /**
     * 获取两个定点的距离
     *
     * @param x X坐标
     * @param y Y坐标
     * @return 0表示不可达
     */
    private int getDistance(int x, int y) {
        return map[x][y];
    }

    /**
     * 采用深度优先算法对图进行遍历
     *
     * @param x           开始点的X坐标
     * @param end         结束点
     * @param path        规划中的路线
     * @param distance    规划中的路线的长度
     * @param maxStops    限定条件：最大站点
     * @param maxDistance 限定条件：最大距离
     */
    private void dfs(int x, char end, String path, int distance, int maxStops, int maxDistance) {
        for (int y = 0; y < map[x].length; y++)
            if (map[x][y] > 0) {
                String currentRoute = path + (char) (y + DirectGraphConstans.ORIGINAL_NODE);
                Integer currentDistance = distance + map[x][y];
                //最大站点限定
                Boolean maxStopsLimit = maxStops > 0 ? (currentRoute.length() - 1 <= maxStops) : true;
                //最大距离限定
                Boolean maxDistanceLimit = maxDistance > 0 ? (currentDistance < maxDistance) : currentDistance < calMaxDistance();
                if (map[x][y] > 0 && maxStopsLimit && maxDistanceLimit) {
                    // 如果到达了结束点
                    if ((char) (y + DirectGraphConstans.ORIGINAL_NODE) == end) {
                        routes.add(currentRoute);
                    }
                    dfs(y, end, currentRoute, currentDistance, maxStops, maxDistance);
                }
            }
    }

    /**
     * 计算图可达性的最大路径,超过该值的可达性计算则为重复规划，没有实际意义
     *
     * @return
     */
    private Integer calMaxDistance() {
        Integer total = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int distance = this.getDistance(i, j);
                if (distance > 0)
                    total = total + distance;
            }
        }
        return total;
    }

}

