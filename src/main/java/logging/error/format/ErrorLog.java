package logging.error.format;

public class ErrorLog {
    private String severity;
    private int Code;

    public ErrorLog(String severity, int code){
        this.severity = severity;
        this.Code = code;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public static ErrorLog ErrorLog(String severity, int code){
        return new ErrorLog(severity, code);
    }
}
