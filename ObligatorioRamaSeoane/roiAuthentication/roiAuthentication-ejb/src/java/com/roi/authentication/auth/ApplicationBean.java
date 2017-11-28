
package com.roi.authentication.auth;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class ApplicationBean {
    @PersistenceContext
    protected EntityManager em;
    
    public boolean HasPermission(String token1, String token2){
        boolean hasPermission = false;
        try{
            AppAuth app1 = (AppAuth)em.createNativeQuery("select * from AppAuth where token='"
                    + token1 + "'",AppAuth.class).getResultList().get(0);
            AppAuth app2 = (AppAuth)em.createNativeQuery("select * from AppAuth where token='"
                    + token2 + "'",AppAuth.class).getResultList().get(0);
       
           if(app1 != null && app2!= null){
             int countResult =  em.createNativeQuery("select * from APPLICATION_PERMISSIONS as a where a.APPAUTH_ID=" + app1.getID() +"and a.HASACCESSTO_ID=" + app2.getID()).getResultList().size();
             if(countResult > 0){
                 hasPermission= true;
             }else{
                  hasPermission= false;
             }
           }else{
               hasPermission= false;
           }
        }catch(Exception e){
            hasPermission = false;
        }
        return hasPermission;
    }
    
}
