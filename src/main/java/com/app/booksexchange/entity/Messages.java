/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.booksexchange.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONObject;

/**
 *
 * @author imad.bakli
 */
@Entity
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findByMessageId", query = "SELECT m FROM Messages m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Messages.findByMessage", query = "SELECT m FROM Messages m WHERE m.message = :message"),
    @NamedQuery(name = "Messages.findByIsRead", query = "SELECT m FROM Messages m WHERE m.isRead = :isRead"),
    @NamedQuery(name = "Messages.findByDate", query = "SELECT m FROM Messages m WHERE m.date = :date")})
public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @Column(name = "is_read")
    private String isRead;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "parentMessage")
    private Collection<Messages> messagesCollection;
    @JoinColumn(name = "parent_message", referencedColumnName = "message_id")
    @ManyToOne
    private Messages parentMessage;
    @JoinColumn(name = "transmitter", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users transmitter;
    @JoinColumn(name = "receiver", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users receiver;

    public Messages() {
    }

    public Messages(Integer messageId) {
        this.messageId = messageId;
    }

    public Messages(Integer messageId, String message, String isRead, Date date) {
        this.messageId = messageId;
        this.message = message;
        this.isRead = isRead;
        this.date = date;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    public Messages getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(Messages parentMessage) {
        this.parentMessage = parentMessage;
    }

    public Users getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(Users transmitter) {
        this.transmitter = transmitter;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	
    	JSONObject obj = new JSONObject();
    	obj.put("message_id", messageId);
    	obj.put("transmitter", new JSONObject(transmitter));
    	obj.put("receiver", new JSONObject(receiver));
    	obj.put("isRead", isRead);
    	obj.put("date", date);
    	obj.put("messageParent", new JSONObject(parentMessage));
        return obj.toString();
    }
    
}
