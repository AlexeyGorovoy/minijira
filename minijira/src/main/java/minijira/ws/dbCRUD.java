package minijira.ws;

//import ejb.database.DatabaseController;

import ejb.database.DatabaseController;
import ejb.database.model.ModelEntity;
import ejb.database.model.Tech;
import ejb.database.model.User;
import ejb.util.Log;
import minijira.web.DatabaseBean;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 23.02.14
 * Time: 11:20
 * Email: alexey.gorovoy.work@gmail.com
 */
@Stateless
@WebService (serviceName = "minijiraWS")
@SuppressWarnings("unchecked")
public class dbCRUD {

    @Inject
    DatabaseController dc;

    @WebMethod(operationName = "getTechsList")
    public Tech[] getTechs(){
        LinkedList<Tech> list = new LinkedList<Tech>(dc.getTech());
        Tech[] techs = list.toArray(new Tech[list.size()]);
        return techs;
    }

    @WebMethod(operationName = "getFirstTech")
    public Tech getFirstTechs(){
        LinkedList<Tech> list = new LinkedList<Tech>(dc.getTech());
        return list.get(0);
    }
    /*
        <T> T find(Class<T> tClass, Object id);
        <T> T merge(T tObject);
        <T> void remove(T tObject);
    */
    @WebMethod(operationName = "mergeTech")
    public void mergeTech(Tech tech){
        dc.merge(tech);
    }

    @WebMethod(operationName = "removeTech")
    public void removeTech(Tech tech){
        dc.remove(tech);
    }

    @WebMethod(operationName = "login")
    public String login(String email, String hash){
        Log.getLogger().info("WS Login for email: " + email + "; hash: " + hash);
        if(email != null && hash != null){

            User user = dc.find(User.class, email);

            if (user != null) {
                Log.getLogger().info("WS Login user found with hash: " + user.getPassword());
                if (user.getPassword().equals(hash)) {
                    return "OK";
                }
            }
        }
        return "FAIL";
    }

    @WebMethod(operationName = "sayHello")
    public String sayHello(@WebParam(name="guestName") String guestName){
        Log.getLogger().info("dc is " + dc);
        if(guestName==null){
            return "Hello";
        }
        return "Hello "+ guestName;
    }
}