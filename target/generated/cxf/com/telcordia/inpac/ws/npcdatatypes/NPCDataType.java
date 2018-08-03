
package com.telcordia.inpac.ws.npcdatatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NPCDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NPCDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MessageHeader" type="{http://ws.inpac.telcordia.com/npcDataTypes}MessageHeaderType"/&gt;
 *         &lt;element name="NPCMessage" type="{http://ws.inpac.telcordia.com/npcDataTypes}NPCMessageType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NPCDataType", propOrder = {
    "messageHeader",
    "npcMessage"
})
public class NPCDataType {

    @XmlElement(name = "MessageHeader", required = true)
    protected MessageHeaderType messageHeader;
    @XmlElement(name = "NPCMessage", required = true)
    protected NPCMessageType npcMessage;

    /**
     * Gets the value of the messageHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MessageHeaderType }
     *     
     */
    public MessageHeaderType getMessageHeader() {
        return messageHeader;
    }

    /**
     * Sets the value of the messageHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageHeaderType }
     *     
     */
    public void setMessageHeader(MessageHeaderType value) {
        this.messageHeader = value;
    }

    /**
     * Gets the value of the npcMessage property.
     * 
     * @return
     *     possible object is
     *     {@link NPCMessageType }
     *     
     */
    public NPCMessageType getNPCMessage() {
        return npcMessage;
    }

    /**
     * Sets the value of the npcMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link NPCMessageType }
     *     
     */
    public void setNPCMessage(NPCMessageType value) {
        this.npcMessage = value;
    }

}
