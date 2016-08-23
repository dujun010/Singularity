package qid.exception;


public class Test {
	public static void main(String[] args) throws ExistedException {
		if(true) throw new ExistedException("选择取水用户错误！");
	}
}
