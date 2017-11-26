package com.roi.planner.programmer;

import com.roi.planner.stretches.Stretch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ActuatorProgramming implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String actuatorId;
    
   @OneToMany(cascade = CascadeType.PERSIST)
   private List<CommandValue> commands  = new ArrayList<>();

    public String getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(String actuatorId) {
        this.actuatorId = actuatorId;
    }

    public List<CommandValue> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandValue> commands) {
        this.commands = commands;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
}
