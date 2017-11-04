package middlem.person.utilslib;

import java.io.Serializable;

/***********************************************
 * <P> desc:   消息推送接收到的内容
 * <P> Author: gongtong
 * <P> Date: 2017/11/1 16:58
 * <P> Copyright  2008 二维火科技
 ***********************************************/

public class MissileMessage implements Serializable {
    /**
     * sourceId : 999280115f567566015f7b7aaac90b90
     * messageSubscId : 999280075f4d064f015f7b7b679405e0
     * sourceFromJPush : JPush
     * messageType : 306
     * unreadCount : 0 未读消息数
     * entityId : 99928007
     * selfEntityId : 99928007
     */
    private String sourceId;
    private String messageSubscId;
    private String sourceFromJPush;
    private String messageType;
    private String unreadCount;
    private String entityId;
    private String selfEntityId;
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMessageSubscId() {
        return messageSubscId;
    }

    public void setMessageSubscId(String messageSubscId) {
        this.messageSubscId = messageSubscId;
    }

    public String getSourceFromJPush() {
        return sourceFromJPush;
    }

    public void setSourceFromJPush(String sourceFromJPush) {
        this.sourceFromJPush = sourceFromJPush;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(String unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getSelfEntityId() {
        return selfEntityId;
    }

    public void setSelfEntityId(String selfEntityId) {
        this.selfEntityId = selfEntityId;
    }

    @Override
    public String toString() {
        return "MissileMessage{" +
                "sourceId='" + sourceId + '\'' +
                ", messageSubscId='" + messageSubscId + '\'' +
                ", sourceFromJPush='" + sourceFromJPush + '\'' +
                ", messageType='" + messageType + '\'' +
                ", unreadCount='" + unreadCount + '\'' +
                ", entityId='" + entityId + '\'' +
                ", selfEntityId='" + selfEntityId + '\'' +
                '}';
    }
}
