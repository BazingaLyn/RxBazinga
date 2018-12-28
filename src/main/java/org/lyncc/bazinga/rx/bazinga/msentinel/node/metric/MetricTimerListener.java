package org.lyncc.bazinga.rx.bazinga.msentinel.node.metric;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.ClusterNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.cluster.ClusterBuilderSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author liguolin
 * @create 2018-12-27 15:29
 **/
public class MetricTimerListener implements Runnable {

    private static final MetricWriter metricWriter = new MetricWriter(SentinelConfig.singleMetricFileSize(),
            SentinelConfig.totalMetricFileCount());

    @Override
    public void run() {

        Map<Long,List<MetricNode>> maps = new TreeMap<>();

        for(Map.Entry<ResourceWrapper,ClusterNode> e : ClusterBuilderSlot.getClusterNodeMap().entrySet()){

            String name = e.getKey().getName();
            ClusterNode node = e.getValue();

            Map<Long, MetricNode> metrics = node.metrics();

            for(Map.Entry<Long,MetricNode> entry :metrics.entrySet()){

                Long time = entry.getKey();
                MetricNode metricNode = entry.getValue();

                metricNode.setResource(name);
                if(maps.get(time) == null){
                    maps.put(time,new ArrayList<MetricNode>());
                }
                List<MetricNode> metricNodes = maps.get(time);
                metricNodes.add(metricNode);
            }
        }
        if(!maps.isEmpty()){
            for(Map.Entry<Long,List<MetricNode>> entry :maps.entrySet()){
                try{
                    metricWriter.write(entry.getKey(),entry.getValue());
                }catch (Exception e){
                    RecordLog.warn("[MetricTimerListener] Write metric error", e);
                }
            }
        }
    }
}
