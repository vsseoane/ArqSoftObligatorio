package com.roi.planner.stretches;

import com.roi.planner.planes.Plan;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T06:01:20")
@StaticMetamodel(Stretch.class)
public class Stretch_ { 

    public static volatile SingularAttribute<Stretch, String> sourceId;
    public static volatile SingularAttribute<Stretch, Long> ID;
    public static volatile SingularAttribute<Stretch, String> sectionId;
    public static volatile SingularAttribute<Stretch, String> actuatorId;
    public static volatile SingularAttribute<Stretch, Plan> plan;

}