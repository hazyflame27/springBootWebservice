
package com.telcordia.inpac.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.telcordia.inpac.ws.npcdatatypes.AuthDetailsType;
import com.telcordia.inpac.ws.npcdatatypes.NPCDataType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthDetails" type="{http://ws.inpac.telcordia.com/npcDataTypes}AuthDetailsType"/&gt;
 *         &lt;element name="NPCMessage" type="{http://ws.inpac.telcordia.com/npcDataTypes}NPCDataType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authDetails",
    "npcMessage"
})
@XmlRootElement(name = "processMessage")
public class ProcessMessage {

    @XmlElement(name = "AuthDetails", required = true)
    protected AuthDetailsType authDetails;
    @XmlElement(name = "NPCMessage", required = true)
    protected NPCDataType npcMessage;

    /**
     * Gets the value of the authDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AuthDetailsType }
     *     
     */
    public AuthDetailsType getAuthDetails() {
        return authDetails;
    }

    /**
     * Sets the value of the authDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthDetailsType }
     *     
     */
    public void setAuthDetails(AuthDetailsType value) {
        this.authDetails = value;
    }

    /**
     * Gets the value of the npcMessage property.
     * 
     * @return
     *     possible object is
     *     {@link NPCDataType }
     *     
     */
    public NPCDataType getNPCMessage() {
        return npcMessage;
    }

    /**
     * Sets the value of the npcMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link NPCDataType }
     *     
     */
    public void setNPCMessage(NPCDataType value) {
        this.npcMessage = value;
    }

}
