package io.toy.core.exception;

public class FileStorageException extends RuntimeException{

private static final long serialVersionUID = -921453796610411683L;
	
	private Object invoke;
    private int errorCode;

    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String message, Object invoke){
        super(message);
        this.invoke = invoke;
    }

    public FileStorageException(String message, Object invoke, Integer errorCode){
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
