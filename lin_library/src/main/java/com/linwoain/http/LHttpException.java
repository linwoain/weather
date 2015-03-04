/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/19 20:42
 */
package com.linwoain.http;

/**
 * @author linwoain
 * @version 2014/10/19 20:42
 */
public class LHttpException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8176392896168700641L;

	public LHttpException(){
        super();
    }


    public LHttpException(String msg) {
        super(msg);
    }
 
    public LHttpException(String msg,Throwable throwable){
        super(msg,throwable);
    }
}
