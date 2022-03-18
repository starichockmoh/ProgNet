package tech.vinc3nzo.prognet.models;

import java.util.List;

/**
 * A compatibility layer object between the
 * old API and the new one.
 */
public class CommonResponseObject {
    private Object data;
    private List<String> messages;
    private List<FieldErrorObject> fieldsErrors;
    private Integer resultCode;

    protected CommonResponseObject() { }

    public CommonResponseObject(Object data, List<String> messages, List<FieldErrorObject> fieldsErrors, Integer resultCode) {
        this.data = data;
        this.messages = messages;
        this.fieldsErrors = fieldsErrors;
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<FieldErrorObject> getFieldsErrors() {
        return fieldsErrors;
    }

    public void setFieldsErrors(List<FieldErrorObject> fieldsErrors) {
        this.fieldsErrors = fieldsErrors;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ResponseObject[" +
                "data=" + data +
                ", messages=" + messages +
                ", fieldsErrors=" + fieldsErrors +
                ", resultCode=" + resultCode +
                ']';
    }
}