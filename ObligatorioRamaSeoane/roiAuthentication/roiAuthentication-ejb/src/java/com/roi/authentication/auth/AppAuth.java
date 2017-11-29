
package com.roi.authentication.auth;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AppAuth implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String token;
    @JoinTable(name = "APPLICATION_PERMISSIONS")
    @ManyToMany()
    private List<AppAuth> hasAccessTo;
    
    public AppAuth() {
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AppAuth> getHasAccessTo() {
        return hasAccessTo;
    }

    public void setHasAccessTo(List<AppAuth> hasAccessTo) {
        this.hasAccessTo = hasAccessTo;
    }
     
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<AppAuth> getAccessTo() {
        return hasAccessTo;
    }
    
    public void setAccessTo(List<AppAuth> accessTo) {
        this.hasAccessTo = accessTo;
    }
    

    
}
