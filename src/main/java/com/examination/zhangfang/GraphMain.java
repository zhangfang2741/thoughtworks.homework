package com.examination.zhangfang;

import com.examination.zhangfang.constans.DirectGraphConstans;
import com.examination.zhangfang.service.ExtendGraphService;
import com.examination.zhangfang.service.impl.BridgeGraphServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphMain.class);

    public static void main(String[] args) {
        /**
         * 初始化定义ABCDE的二维坐标图  AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 .
         **/
        int[][] map = {
                /**       A  B  C  D  E **/
                /** A **/{0, 5, 0, 5, 7},
                /** B **/{0, 0, 4, 0, 0},
                /** C **/{0, 0, 0, 8, 2},
                /** D **/{0, 0, 8, 0, 6},
                /** E **/{0, 3, 0, 0, 0}
        };
        //通过桥接对象处理
        ExtendGraphService graphService = new BridgeGraphServiceImpl(map);
        //1、The distance of the route A-B-C.
        char[] route1 = {'A', 'B', 'C'};
        String distance1 = graphService.getDistanceWithUnreachable(route1);
        LOGGER.info("{}", distance1);

        //2、The distance of the route A-D.
        char[] route2 = {'A', 'D'};
        String distance2 = graphService.getDistanceWithUnreachable(route2);
        LOGGER.info("{}", distance2);

        //3、The distance of the route A-D-C.
        char[] route3 = {'A', 'D', 'C'};
        String distance3 = graphService.getDistanceWithUnreachable(route3);
        LOGGER.info("{}", distance3);

        //4、The distance of the route A-E-B-C-D.
        char[] route4 = {'A', 'E', 'B', 'C', 'D'};
        String distance4 = graphService.getDistanceWithUnreachable(route4);
        LOGGER.info("{}", distance4);

        //5、The distance of the route A-E-D
        char[] route5 = {'A', 'E', 'D'};
        String distance5 = graphService.getDistanceWithUnreachable(route5);
        LOGGER.info("{}", distance5);

        // 6、The number of trips starting at C and ending at C with a maximum of 3 stops
        int routesNum6 = graphService.getRoutesNum('C', 'C', 3, DirectGraphConstans.UNLIMITED_DISTANCE);
        LOGGER.info("{}", routesNum6);

        // 7、The number of trips starting at A and ending at C with exactly 4 stops
        int routesNum7 = graphService.getExactlyStopRouteNum('A', 'C', 4, DirectGraphConstans.UNLIMITED_DISTANCE);
        LOGGER.info("{}", routesNum7);

        // 8、The length of the shortest route (in terms of distance to travel) from A to C.
        int shortestRouteLength8 = graphService.getShortestRouteLength('A', 'C', DirectGraphConstans.UNLIMITED_STOPS, DirectGraphConstans.UNLIMITED_DISTANCE);
        LOGGER.info("{}", shortestRouteLength8);

        // 9、The length of the shortest route (in terms of distance to travel) from B to B.
        int shortestRouteLength9 = graphService.getShortestRouteLength('B', 'B', DirectGraphConstans.UNLIMITED_STOPS, DirectGraphConstans.UNLIMITED_DISTANCE);
        LOGGER.info("{}", shortestRouteLength9);

        // 10、The number of different routes from C to C with a distance of less than 30
        int routesNum10 = graphService.getRoutesNum('C', 'C', 9, 30);
        LOGGER.info("{}", routesNum10);

    }
}
