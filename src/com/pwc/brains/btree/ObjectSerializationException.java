package com.pwc.brains.btree;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 4/29/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectSerializationException extends Exception {

    public ObjectSerializationException(String message, Throwable cause) {
        super(message, cause);

    }
    public ObjectSerializationException(String message) {
        super(message);
    }
}
