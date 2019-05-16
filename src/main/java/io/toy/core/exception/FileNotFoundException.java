package io.toy.core.exception;

public class FileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -921453796610411683L;
	
	private Object invoke;
    private int errorCode;

    public FileNotFoundException(String message){
        super(message);
    }

    public FileNotFoundException(String message, Object invoke){
        super(message);
        this.invoke = invoke;
    }

    public FileNotFoundException(String message, Object invoke, Integer errorCode){
        super(message);
        this.invoke = invoke;
        this.errorCode = errorCode;
    }

    public Object getInvoke() {
        return invoke;
    }

    public Object getErrorCode() {
        return errorCode;
    }

}
