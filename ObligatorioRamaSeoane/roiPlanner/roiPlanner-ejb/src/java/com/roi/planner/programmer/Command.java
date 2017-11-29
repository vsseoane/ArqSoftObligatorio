package com.roi.planner.programmer;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String typeValueCommand;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ActuatorProgramming actuatorProgramming;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeValueCommand() {
        return typeValueCommand;
    }

    public void setTypeValueCommand(String typeValueCommand) {
        this.typeValueCommand = typeValueCommand;
    }
}
