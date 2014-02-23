
package minijira.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the minijira.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemoveTech_QNAME = new QName("http://ws.minijira/", "removeTech");
    private final static QName _GetTechsList_QNAME = new QName("http://ws.minijira/", "getTechsList");
    private final static QName _GetTechsListResponse_QNAME = new QName("http://ws.minijira/", "getTechsListResponse");
    private final static QName _GetFirstTechResponse_QNAME = new QName("http://ws.minijira/", "getFirstTechResponse");
    private final static QName _MergeTech_QNAME = new QName("http://ws.minijira/", "mergeTech");
    private final static QName _SayHello_QNAME = new QName("http://ws.minijira/", "sayHello");
    private final static QName _SayHelloResponse_QNAME = new QName("http://ws.minijira/", "sayHelloResponse");
    private final static QName _GetFirstTech_QNAME = new QName("http://ws.minijira/", "getFirstTech");
    private final static QName _RemoveTechResponse_QNAME = new QName("http://ws.minijira/", "removeTechResponse");
    private final static QName _MergeTechResponse_QNAME = new QName("http://ws.minijira/", "mergeTechResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: minijira.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MergeTech }
     * 
     */
    public MergeTech createMergeTech() {
        return new MergeTech();
    }

    /**
     * Create an instance of {@link MergeTechResponse }
     * 
     */
    public MergeTechResponse createMergeTechResponse() {
        return new MergeTechResponse();
    }

    /**
     * Create an instance of {@link RemoveTech }
     * 
     */
    public RemoveTech createRemoveTech() {
        return new RemoveTech();
    }

    /**
     * Create an instance of {@link Tech }
     * 
     */
    public Tech createTech() {
        return new Tech();
    }

    /**
     * Create an instance of {@link RemoveTechResponse }
     * 
     */
    public RemoveTechResponse createRemoveTechResponse() {
        return new RemoveTechResponse();
    }

    /**
     * Create an instance of {@link GetFirstTechResponse }
     * 
     */
    public GetFirstTechResponse createGetFirstTechResponse() {
        return new GetFirstTechResponse();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link GetTechsList }
     * 
     */
    public GetTechsList createGetTechsList() {
        return new GetTechsList();
    }

    /**
     * Create an instance of {@link GetFirstTech }
     * 
     */
    public GetFirstTech createGetFirstTech() {
        return new GetFirstTech();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link GetTechsListResponse }
     * 
     */
    public GetTechsListResponse createGetTechsListResponse() {
        return new GetTechsListResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveTech }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "removeTech")
    public JAXBElement<RemoveTech> createRemoveTech(RemoveTech value) {
        return new JAXBElement<RemoveTech>(_RemoveTech_QNAME, RemoveTech.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTechsList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "getTechsList")
    public JAXBElement<GetTechsList> createGetTechsList(GetTechsList value) {
        return new JAXBElement<GetTechsList>(_GetTechsList_QNAME, GetTechsList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTechsListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "getTechsListResponse")
    public JAXBElement<GetTechsListResponse> createGetTechsListResponse(GetTechsListResponse value) {
        return new JAXBElement<GetTechsListResponse>(_GetTechsListResponse_QNAME, GetTechsListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFirstTechResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "getFirstTechResponse")
    public JAXBElement<GetFirstTechResponse> createGetFirstTechResponse(GetFirstTechResponse value) {
        return new JAXBElement<GetFirstTechResponse>(_GetFirstTechResponse_QNAME, GetFirstTechResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeTech }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "mergeTech")
    public JAXBElement<MergeTech> createMergeTech(MergeTech value) {
        return new JAXBElement<MergeTech>(_MergeTech_QNAME, MergeTech.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFirstTech }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "getFirstTech")
    public JAXBElement<GetFirstTech> createGetFirstTech(GetFirstTech value) {
        return new JAXBElement<GetFirstTech>(_GetFirstTech_QNAME, GetFirstTech.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveTechResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "removeTechResponse")
    public JAXBElement<RemoveTechResponse> createRemoveTechResponse(RemoveTechResponse value) {
        return new JAXBElement<RemoveTechResponse>(_RemoveTechResponse_QNAME, RemoveTechResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeTechResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.minijira/", name = "mergeTechResponse")
    public JAXBElement<MergeTechResponse> createMergeTechResponse(MergeTechResponse value) {
        return new JAXBElement<MergeTechResponse>(_MergeTechResponse_QNAME, MergeTechResponse.class, null, value);
    }

}
