package middlem.person.utilslib;

/***********************************************
 * <P> desc:
 * <P> Author: gongtong
 * <P> Date: 2017/11/2 22:21
 * <P> Copyright  2008 二维火科技
 ***********************************************/

public class Test {


    /**
     * alert : 有新的采购单
     * extras : {"sourceId":"999280115f567566015f7cb61e040d62","messageSubscId":"999280075f4d064f015f7cb642940740","sourceFromJPush":"JPush","messageType":"306","unreadCount":"1","entityId":"99928007","selfEntityId":"99928007"}
     * title : 有新的采购单
     */

    private String alert;
    private String extras;
    private String title;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class ExtrasBean {
        /**
         * sourceId : 999280115f567566015f7cb61e040d62
         * messageSubscId : 999280075f4d064f015f7cb642940740
         * sourceFromJPush : JPush
         * messageType : 306
         * unreadCount : 1
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
    }
}
