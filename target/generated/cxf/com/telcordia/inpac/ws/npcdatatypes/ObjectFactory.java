
package com.telcordia.inpac.ws.npcdatatypes;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.telcordia.inpac.ws.npcdatatypes package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.telcordia.inpac.ws.npcdatatypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthDetailsType }
     * 
     */
    public AuthDetailsType createAuthDetailsType() {
        return new AuthDetailsType();
    }

    /**
     * Create an instance of {@link NPCDataType }
     * 
     */
    public NPCDataType createNPCDataType() {
        return new NPCDataType();
    }

    /**
     * Create an instance of {@link RetResultType }
     * 
     */
    public RetResultType createRetResultType() {
        return new RetResultType();
    }

    /**
     * Create an instance of {@link MessageHeaderType }
     * 
     */
    public MessageHeaderType createMessageHeaderType() {
        return new MessageHeaderType();
    }

    /**
     * Create an instance of {@link NPCMessageType }
     * 
     */
    public NPCMessageType createNPCMessageType() {
        return new NPCMessageType();
    }

}
