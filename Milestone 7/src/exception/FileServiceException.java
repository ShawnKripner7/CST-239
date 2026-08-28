package exception;

/**
 * Custom exception used when inventory file operations fail.
 *
 * @author Shawn Kripner
 */
public class FileServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new file service exception.
     *
     * @param message the error message
     */
    public FileServiceException(String message) {
        super(message);
    }

    /**
     * Creates a new file service exception with an underlying cause.
     *
     * @param message the error message
     * @param cause the original exception
     */
    public FileServiceException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}