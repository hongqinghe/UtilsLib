package middlem.person.utilslib;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 消息中心推送消息
 * Created by 玳瑁 on 2017/1/11.
 */

public class MessageVo implements Serializable {

    /**
     * content : asdasasdads
     * createTime : 2016 1003105941588
     * isRead : 0
     * messageType : 300
     * title : 阿斯顿
     */
    public static final int SUPPLIER_PASS = 100;//供应商审核通过消息
    public static final int SUPPLIER_DENY = 101;//供应商审核被拒绝消息
    public static final int SUPPLIER_FREEZE = 102;//供应商资格被冻结消息
    public static final int SUPPLIER_ACTIVE = 103;//供应商资格被激活消息
    public static final int SUPPLIER_OVERDUE = 104;//过期未支付保证金需重新提交审核的消息
    public static final int GOODS_PASS = 200;//商品审核（标准原料）通过消息
    public static final int GOODS_DENY = 201;//商品审核（标准原料）被拒绝消息
    public static final int GOODS_EMPTY = 202;//平台可销售数量为0，需再设置数量消息
    public static final int SUPPLIER_GOODS_SEND = 300;//供货商已发货消息（买方）
    public static final int SUPPLIER_RETURN_AGREE = 301;//供货商同意退货消息（买方）
    public static final int SUPPLIER_RETURN_DENY = 302;//供货商拒绝退货消息（买方）
    public static final int SUPPLIER_RETUURN_CONFIRM = 303;//供应商确认退货消息（买方）
    public static final int GOODS_SEND = 304;//有新的订单，需配送消息（卖方）
    public static final int GOODS_RETURN = 305;//有新的订单，有申请退货，需处理退货单（卖方）
    public static final int SUPPLY_PURCHASE_ORDER_OF_STORE = 306;//销售订单
    public static final int SUPPLY_DISTRIBUTION_LIST = 307;//配送发货单
    public static final int SUPPLY_GODOWN_ENTRY = 308;//入库单
    public static final int SUPPLY_PAYMENT_FAILED= 309;//支付失败通知
    public static final int SUPPLY_STOCK_STOCK_LIST= 310;//仓库盘存单列表，查询条件的状态默认为“盘存中”
    public static final int SUPPLY_STOCK_CANCEL_STAOCK= 311;//仓库盘存单列表，查询条件的状态默认为“取消盘存”
    public static final int SUPPLY_NEED_SHOP_PAY= 312;//平台订单生成成功，需要买方（门店）支付
    public static final int SUPPLY_NEED_HQ_OPERATE= 313;//买家支付成功，需要总部处理


    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息是否已读
     */
    private short isRead;
    /**
     * 消息类型
     */
    private String messageType;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 消息订阅ID
     */
    private String messageSubscId;
    /**
     * targetUserId : 9992986758d3fa78015901d645640009
     * messageType : 303
     * isRead : 1
     * params : {"buyerSelfEntityId":"99929867","orderNo":"S201702240000009"}
     * readed : true
     */

    private String targetUserId;

    @SerializedName("messageType")
    private int messageTypeX;

    @SerializedName("isRead")
    private int isReadX;

    private ParamsBean params;
    private boolean readed;

    public String getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(String unreadCount) {
        this.unreadCount = unreadCount;
    }

    /**
     * 未读消息数量
     */
    private String unreadCount;

    /**
     * 消息订阅ID
     */
    private String sourceId;

    public String getSelfEntityId() {
        return selfEntityId;
    }

    public void setSelfEntityId(String selfEntityId) {
        this.selfEntityId = selfEntityId;
    }

    private String selfEntityId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public short getIsRead() {
        return isRead;
    }

    public void setIsRead(short isRead) {
        this.isRead = isRead;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageSubscId() {
        return messageSubscId;
    }

    public void setMessageSubscId(String messageSubscId) {
        this.messageSubscId = messageSubscId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }


    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public int getMessageTypeX() {
        return messageTypeX;
    }

    public void setMessageTypeX(int messageTypeX) {
        this.messageTypeX = messageTypeX;
    }

    public int getIsReadX() {
        return isReadX;
    }

    public void setIsReadX(int isReadX) {
        this.isReadX = isReadX;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public boolean hasLink; //显示链接图标

    public boolean getHasLink() {
        return hasLink;
    }

    public void setHasLink(boolean hasLink) {
        this.hasLink = hasLink;
    }

    public static class ParamsBean {
        /**
         * buyerSelfEntityId : 99929867
         * orderNo : S201702240000009
         */

        private String buyerSelfEntityId;
        private String orderNo;
        private String shopName;

        public String getBuyerSelfEntityId() {
            return buyerSelfEntityId;
        }

        public void setBuyerSelfEntityId(String buyerSelfEntityId) {
            this.buyerSelfEntityId = buyerSelfEntityId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

    }
}
