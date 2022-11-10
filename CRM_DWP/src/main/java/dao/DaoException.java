package dao;

public class DaoException extends Exception {

	//Pour l'instant "private static final long serialVersionUID" pas proposé
	
    public DaoException( String message ) {
        super( message );
    }

    public DaoException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DaoException( Throwable cause ) {
        super( cause );
    }

}
