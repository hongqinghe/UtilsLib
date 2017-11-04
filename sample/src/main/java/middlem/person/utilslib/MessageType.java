package middlem.person.utilslib;

public class MessageType {
	
	/**
	 * <code>新任务</code>
	 */
	public final static Short TYPE_NEW_MSG = 1;
	/**
	 * <code>任务被他人处理</code>
	 */
	public final static Short TYPE_UPDATE_MSG = 2;
	/**
	 * <code>任务处理结果</code>
	 */
	public final static Short TYPE_MSG = 3;
	/**
	 * <code>批量任务处理结果</code>
	 */
	public final static Short TYPE_BATCH = 4;
	/**
	 * <code>沽清有变动</code>
	 */
	public final static Short TYPE_BANLANCE = 5;
	/**
	 * <code>座位状态有变动</code>
	 */
	public final static Short TYPE_SEAT = 6;
	/**
	 * <code>消息中心</code>
	 */
	public final static Short TYPE_MESSAGE = 21;
	/**
	 * <code>系统通知</code>
	 */
	public final static String TYPE_SYS_NOTIFICATION = "22";

	/**
	 * <code>下线通知</code>
	 */
	public final static String TYPE_UNBIND_NOTIFICATION = "23";



	private String type;

	/**
	 * 得到任务类型
	 * @return type 任务类型
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 设置任务类型
	 * @param type 任务类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
