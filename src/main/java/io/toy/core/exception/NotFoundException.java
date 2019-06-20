package io.toy.core.exception;

public class NotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 5457020071021462699L;


	private Object invoke;
    private int errorCode;

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message, Object invoke){
        super(message);
        this.invoke = invoke;
    }

    public NotFoundException(String message, Object invoke, Integer errorCode){
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
