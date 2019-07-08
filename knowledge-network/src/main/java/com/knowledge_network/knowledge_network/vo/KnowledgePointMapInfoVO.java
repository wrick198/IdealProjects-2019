package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.KnowledgePointRelationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wjk
 * Date: 17-12-28
 * Time: 下午5:00
 */
public class KnowledgePointMapInfoVO {
    private Collection<KnowledgePointMapVO> knowledgePointMapVOS;
    private Collection<KnowledgePointMapEdgeVO> knowledgePointEdgeVOS;

    public KnowledgePointMapInfoVO() {
    }


    public KnowledgePointMapInfoVO(Collection<KnowledgePoint> knowledgePoints) {
        List<KnowledgePointMapVO> knowledgePointMapVOList = new ArrayList<>();
        List<KnowledgePointMapEdgeVO> knowledgePointMapEdgeVOList = new ArrayList<>();
        for (KnowledgePoint knowledgePoint : knowledgePoints) {
            knowledgePointMapVOList.add(new KnowledgePointMapVO(knowledgePoint));
            Collection<KnowledgePointRelationship> knowledgePointRelationships = knowledgePoint.getKnowledgePointRelationships1();
            if (knowledgePointRelationships.size() > 0)
                for (KnowledgePointRelationship knowledgePointRelationship : knowledgePointRelationships) {
                    if (knowledgePointRelationship != null)
                        knowledgePointMapEdgeVOList.add(new KnowledgePointMapEdgeVO(knowledgePointRelationship));
                }
        }
        this.knowledgePointMapVOS = knowledgePointMapVOList;
        this.knowledgePointEdgeVOS = knowledgePointMapEdgeVOList;
    }

    public Collection<KnowledgePointMapVO> getKnowledgePointMapVOS() {
        return knowledgePointMapVOS;
    }

    public void setKnowledgePointMapVOS(Collection<KnowledgePointMapVO> knowledgePointMapVOS) {
        this.knowledgePointMapVOS = knowledgePointMapVOS;
    }

    public Collection<KnowledgePointMapEdgeVO> getKnowledgePointEdgeVOS() {
        return knowledgePointEdgeVOS;
    }

    public void setKnowledgePointEdgeVOS(Collection<KnowledgePointMapEdgeVO> knowledgePointEdgeVOS) {
        this.knowledgePointEdgeVOS = knowledgePointEdgeVOS;
    }
}
