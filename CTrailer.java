/**
 * Vehicle Rental
 * 
 * @author Robert Firor
 * @version 1.0
 * @since 12/09/2025
 */
public class CTrailer extends CVehicle{
	/**
	 * Abstract Initialize method
	 * @param m_intNumberOfWheels 
	 * @param m_intNumberOfMPG
	 * @param m_dblPrice
	 */
	public void Initialize( int m_intNumberOfWheels, int m_intNumberOfMPG, float m_dblPrice )
	{
		//protected: can be accessed only by the class in which it is declared or by classes that inherit. 
		setM_strType( "Trailer" );
		setM_intNumberOfWheels( m_intNumberOfWheels );
		setM_intNumberOfMPG ( m_intNumberOfMPG );
		setM_dblPrice ( m_dblPrice );
	}
	
	/**
	 * Abstract - CTrailer Constructor no parameters
	 */
	public CTrailer() {
		Initialize( 0,0,0 );
	}
	
	/**
	 * Abstract - CTrailer Constructor
	 * @param m_intNumberOfWheels
	 */
	public CTrailer( int m_intNumberOfWheels ) { 
		Initialize( m_intNumberOfWheels,0,0 );
	}
	/**
	 * Abstract - CTrailer Constructor
	 * @param m_intNumberOfWheels
	 * @param m_intNumberOfMPG
	 */
	public CTrailer( int m_intNumberOfWheels, int m_intNumberOfMPG ) { 
		Initialize( m_intNumberOfWheels, m_intNumberOfMPG,0 );
	}
	/**
	 * Abstract - CTrailer Constructor
	 * @param m_intNumberOfWheels
	 * @param m_intNumberOfMPG
	 * @param m_dblPrice
	 */
	public CTrailer( int m_intNumberOfWheels, int m_intNumberOfMPG, float m_dblPrice ) { 
		Initialize( m_intNumberOfWheels, m_intNumberOfMPG, m_dblPrice );
	}
	
	
	
	/**
	 * Abstract - get how to drive
	 */
	public void getHowToDrive() {
    	System.out.println( "Get another vehicle to pull" );
    }
	
	
	
	
	
}
