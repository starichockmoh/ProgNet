package tech.vinc3nzo.prognet.models;

/**
 * A helper object for the ResponseObject.
 */
public class FieldErrorObject {
    private String field;
    private String error;

    protected FieldErrorObject() { }

    public FieldErrorObject(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "FieldErrorObject[" +
                "field='" + field + '\'' +
                ", error='" + error + '\'' +
                ']';
    }
}
