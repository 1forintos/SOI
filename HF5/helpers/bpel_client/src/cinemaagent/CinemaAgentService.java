package cinemaagent;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "CinemaAgentService", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", wsdlLocation = "file:CinemaAgent.wsdl")
public class CinemaAgentService extends Service {

    private final static URL CINEMAAGENTSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException CINEMAAGENTSERVICESERVICE_EXCEPTION;
    private final static QName CINEMAAGENTSERVICESERVICE_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent", "CinemaAgentService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:CinemaAgent.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CINEMAAGENTSERVICESERVICE_WSDL_LOCATION = url;
        CINEMAAGENTSERVICESERVICE_EXCEPTION = e;
    }

    public CinemaAgentService() {
        super(__getWsdlLocation(), CINEMAAGENTSERVICESERVICE_QNAME);
    }

    public CinemaAgentService(URL wsdlLocation) {
        super(wsdlLocation, CINEMAAGENTSERVICESERVICE_QNAME);
    }

    public CinemaAgentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    @WebEndpoint(name = "ICinemaAgent_HttpSoap11_Port")
    public ICinemaAgent getICinemaAgentHttpSoap11Port() {
        return super.getPort(new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent", "ICinemaAgent_HttpSoap11_Port"), ICinemaAgent.class);
    }

    @WebEndpoint(name = "ICinemaAgent_HttpSoap11_Port")
    public ICinemaAgent getICinemaAgentHttpSoap11Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent", "ICinemaAgent_HttpSoap11_Port"), ICinemaAgent.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CINEMAAGENTSERVICESERVICE_EXCEPTION!= null) {
            throw CINEMAAGENTSERVICESERVICE_EXCEPTION;
        }
        return CINEMAAGENTSERVICESERVICE_WSDL_LOCATION;
    }
}
