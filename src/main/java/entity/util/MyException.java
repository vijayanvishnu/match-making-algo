package entity.util;

public class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}
	public String toString() {
		return super.getMessage();
	}
}
