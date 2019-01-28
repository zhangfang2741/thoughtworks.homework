package com.examination.zhangfang.service;

public interface ExtendGraphService extends GraphService {

    /**
     * 获取指定路线的距离，不可达返回：NO SUCH ROUTE
     *
     * @param route 指定路线 如：{'A','B','C'}
     * @return 不可达返回：NO SUCH ROUTE
     */
     String getDistanceWithUnreachable(char[] route);

    /**
     * 获取符合条件的线路数量
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return             符合条件的线路集合
     */
     Integer getRoutesNum(char start, char end, int stops, int maxDistance);
    /**
     * 获取符合条件的最短线路的距离长度
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return             符合条件的线路集合
     */
    Integer getShortestRouteLength(char start, char end, int stops, int maxDistance);
    /**
     * 获取符合指定站点条件的路线条数
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return             符合条件的线路集合
     */
    Integer getExactlyStopRouteNum (char start, char end, int stops, int maxDistance);
}
