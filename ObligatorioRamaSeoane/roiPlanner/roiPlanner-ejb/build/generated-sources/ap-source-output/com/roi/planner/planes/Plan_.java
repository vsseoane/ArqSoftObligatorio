package com.roi.planner.planes;

import com.roi.planner.planes.Stretch;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T20:19:59")
@StaticMetamodel(Plan.class)
public class Plan_ { 

    public static volatile SingularAttribute<Plan, Boolean> isSecondApproved;
    public static volatile SingularAttribute<Plan, Integer> idOrder;
    public static volatile SingularAttribute<Plan, Boolean> isCanceled;
    public static volatile ListAttribute<Plan, Stretch> stretches;
    public static volatile SingularAttribute<Plan, Long> id;
    public static volatile SingularAttribute<Plan, Boolean> isApproved;
    public static volatile SingularAttribute<Plan, Integer> numberPlan;

}