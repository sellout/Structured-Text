package com.valentsolutions.stxt;

/**
 * A catch-all for any exceptions that occur internally to our system.
 */
public class StxtException
    extends Exception
{

    /**
     * Constructor for StxtException.
     */
    public StxtException()
    {
        super();
    }

    /**
     * Constructor for StxtException.
     * @param message
     */
    public StxtException(String message)
    {
        super(message);
    }

    /**
     * Constructor for StxtException.
     * @param message
     * @param cause
     */
    public StxtException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructor for StxtException.
     * @param cause
     */
    public StxtException(Throwable cause)
    {
        super(cause);
    }
}
