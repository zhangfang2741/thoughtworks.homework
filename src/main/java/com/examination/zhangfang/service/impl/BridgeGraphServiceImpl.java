package com.examination.zhangfang.service.impl;

import com.examination.zhangfang.constans.DirectGraphConstans;
import com.examination.zhangfang.service.ExtendGraphService;

import java.util.*;

/**
 * 使用桥接模式
 */
public class BridgeGraphServiceImpl extends GraphServiceImpl implements ExtendGraphService {

    /**
     * 初始化图
     *
     * @param map
     */
    public BridgeGraphServiceImpl(int[][] map) {
        super(map);
    }

    /**
     * 获取指定路线的距离
     *
     * @param route 指定路线 如：{'A','B','C'}
     * @return 不可达返回：NO SUCH ROUTE
     */
    @Override
    public String getDistanceWithUnreachable(char[] route) {
        int distance = this.getDistance(route);
        if (distance == 0)
            return DirectGraphConstans.NO_SUCH_ROUTE;
        return String.valueOf(distance);
}

    /**
     * 获取符合条件的线路数量，如果不具有可达性，则提示：NO SUCH ROUTE
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return 符合条件的线路集合
     */
    @Override
    public Integer getRoutesNum(char start, char end, int stops, int maxDistance) {
        List<String> routes = this.buildRoutes(start, end, stops, maxDistance);
        return routes.size();
    }

    /**
     * 获取符合条件的最短线路的距离长度
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return 符合条件的线路集合
     */
    @Override
    public Integer getShortestRouteLength(char start, char end, int stops, int maxDistance) {
        Map<String, Integer> routeMap = new HashMap<>();
        List<String> routes = this.buildRoutes(start, end, stops, maxDistance);
        for (String route : routes) {
            char[] chars = route.toCharArray();
            int distance = this.getDistance(chars);
            routeMap.put(route, distance);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(routeMap.entrySet());
        //排序
        Collections.sort(list, Comparator.comparingInt(Map.Entry::getValue));

        return list.get(0).getValue();
    }

    /**
     * 获取符合指定站点条件的路线条数
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return 符合条件的线路集合
     */
    @Override
    public Integer getExactlyStopRouteNum(char start, char end, int stops, int maxDistance) {
        List<String> routes = this.buildRoutes(start, end, stops, maxDistance);
        List<String> fileterRoutes = new ArrayList<>();
        for (String route : routes) {
            char[] chars = route.toCharArray();
            if (chars.length == stops + 1) {
                fileterRoutes.add(route);
            }
        }
        return fileterRoutes.size();
    }
}
