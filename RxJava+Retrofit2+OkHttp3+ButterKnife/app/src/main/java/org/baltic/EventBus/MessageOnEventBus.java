package org.baltic.EventBus;

/**
 * Created by Baltic on 2016/10/8.
 */

public class MessageOnEventBus {
    String message;
    String type;
    public static final String TYPE_A ="A";
    public static final String TYPE_B ="B";
    public static final String TYPE_C ="C";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeA() {
        return type;
    }

    public void setTypeA() {
        this.type = TYPE_A;
    }
    public void setTypeB() {
        this.type = TYPE_B;
    }
    public void setTypeC() {
        this.type = TYPE_C;
    }
}
