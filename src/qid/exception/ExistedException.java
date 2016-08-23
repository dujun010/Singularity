package qid.exception;

/**
 * 自定义异常类
 * @Description: 公用的Exception.
 * 				   继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * @History：
 * @author djun
 * @version 1.0
 */
public class ExistedException extends Exception {

	private static final long serialVersionUID = 3583566093089790852L;

	public ExistedException() {
		super();
	}

	public ExistedException(String message) {
		super(message);
	}

	public ExistedException(Throwable cause) {
		super(cause);
	}

	public ExistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
