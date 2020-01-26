package testTask.Exceptions;

public class NotExistRouteException extends Exception {
    public NotExistRouteException() {
        super("NO SUCH ROUTE");
    }
}
