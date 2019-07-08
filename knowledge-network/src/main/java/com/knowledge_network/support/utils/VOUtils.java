package com.knowledge_network.support.utils;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.vo.web.ConditionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pentonbin on 17-12-25
 * <p>
 * 页面数据对象工具类
 */
public class VOUtils {

    /**
     * 由ConditionVO构造一个Condition对象
     *
     * @param conditionVO
     * @return
     */
    public static BaseHibernateDAO.Condition conditionVO2Condition(ConditionVO conditionVO) {
        BaseHibernateDAO.Condition condition = null;
        BaseHibernateDAO.ConditionType type = null;
        if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.EQ.getOp())) {
            type = BaseHibernateDAO.ConditionType.EQ;
        } else if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.LIKE.getOp())) {
            type = BaseHibernateDAO.ConditionType.LIKE;
        } else if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.GT.getOp())) {
            type = BaseHibernateDAO.ConditionType.GT;
        } else if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.GE.getOp())) {
            type = BaseHibernateDAO.ConditionType.GE;
        } else if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.LT.getOp())) {
            type = BaseHibernateDAO.ConditionType.LT;
        } else if (conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.LE.getOp())) {
            type = BaseHibernateDAO.ConditionType.LE;
        }
        if (type != null) {
            condition = new BaseHibernateDAO.Condition(type, conditionVO.getProperty(), conditionVO.getValue());
        }
        return condition;
    }

    public static List<BaseHibernateDAO.Condition> conditionVOs2Conditions(List<ConditionVO> conditionVOS) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        for (ConditionVO cvo : conditionVOS) {
            conditions.add(VOUtils.conditionVO2Condition(cvo));
        }
        return conditions;
    }
}
