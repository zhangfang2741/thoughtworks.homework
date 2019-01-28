package com.examination.zhangfang.service;

import java.util.List;

public interface GraphService {

    /**
     * 获取指定路线的距离
     *
     * @param route 指定路线 如：{'A','B','C'}
     * @return 0表示不可达.
     */
     int getDistance(char[] route);

    /**
     * 构建符合条件的线路
     *
     * @param start:       开始顶点.
     * @param end:         结束顶点.
     * @param stops:       最大站点值.
     * @param maxDistance: 最远距离.
     * @return             符合条件的线路集合
     */
     List<String> buildRoutes(char start, char end, int stops, int maxDistance);


}
