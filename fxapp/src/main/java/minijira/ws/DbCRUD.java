
package minijira.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "dbCRUD", targetNamespace = "http://ws.minijira/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DbCRUD {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://ws.minijira/", className = "minijira.ws.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.LoginResponse")
    @Action(input = "http://ws.minijira/dbCRUD/loginRequest", output = "http://ws.minijira/dbCRUD/loginResponse")
    public String login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @return
     *     returns minijira.ws.Tech
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFirstTech", targetNamespace = "http://ws.minijira/", className = "minijira.ws.GetFirstTech")
    @ResponseWrapper(localName = "getFirstTechResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.GetFirstTechResponse")
    @Action(input = "http://ws.minijira/dbCRUD/getFirstTechRequest", output = "http://ws.minijira/dbCRUD/getFirstTechResponse")
    public Tech getFirstTech();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "removeTech", targetNamespace = "http://ws.minijira/", className = "minijira.ws.RemoveTech")
    @ResponseWrapper(localName = "removeTechResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.RemoveTechResponse")
    @Action(input = "http://ws.minijira/dbCRUD/removeTechRequest", output = "http://ws.minijira/dbCRUD/removeTechResponse")
    public void removeTech(
        @WebParam(name = "arg0", targetNamespace = "")
        Tech arg0);

    /**
     * 
     * @param guestName
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://ws.minijira/", className = "minijira.ws.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.SayHelloResponse")
    @Action(input = "http://ws.minijira/dbCRUD/sayHelloRequest", output = "http://ws.minijira/dbCRUD/sayHelloResponse")
    public String sayHello(
        @WebParam(name = "guestName", targetNamespace = "")
        String guestName);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "mergeTech", targetNamespace = "http://ws.minijira/", className = "minijira.ws.MergeTech")
    @ResponseWrapper(localName = "mergeTechResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.MergeTechResponse")
    @Action(input = "http://ws.minijira/dbCRUD/mergeTechRequest", output = "http://ws.minijira/dbCRUD/mergeTechResponse")
    public void mergeTech(
        @WebParam(name = "arg0", targetNamespace = "")
        Tech arg0);

    /**
     * 
     * @return
     *     returns java.util.List<minijira.ws.Tech>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTechsList", targetNamespace = "http://ws.minijira/", className = "minijira.ws.GetTechsList")
    @ResponseWrapper(localName = "getTechsListResponse", targetNamespace = "http://ws.minijira/", className = "minijira.ws.GetTechsListResponse")
    @Action(input = "http://ws.minijira/dbCRUD/getTechsListRequest", output = "http://ws.minijira/dbCRUD/getTechsListResponse")
    public List<Tech> getTechsList();

}