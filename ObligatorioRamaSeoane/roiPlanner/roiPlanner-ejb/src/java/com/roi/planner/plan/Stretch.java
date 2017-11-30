package com.roi.planner.plan;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Stretch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sectionId;        
    private String actuatorId;
    private String sourceId;    
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Plan plan;
    
    public Stretch() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    
    public String getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(String actuatorId) {
        this.actuatorId = actuatorId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

}
