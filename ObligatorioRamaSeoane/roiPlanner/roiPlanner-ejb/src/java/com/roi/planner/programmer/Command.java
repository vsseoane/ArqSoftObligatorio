
package com.roi.planner.programmer;

import com.roi.planner.planes.Plan;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Name;
    private String TypeValueCommand;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ActuatorProgramming actuatorProgramming;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTypeValueCommand() {
        return TypeValueCommand;
    }

    public void setTypeValueCommand(String TypeValueCommand) {
        this.TypeValueCommand = TypeValueCommand;
    }
    
    
    
}
