/**
 * Vehicle Rental
 * 
 * @author Robert Firor
 * @version 1.0
 * @since 12/09/2025
 */
public class CVehicle {
	private String m_strType;
    private int m_intNumberOfWheels;
    private int m_intNumberOfMPG;
    private float m_dblPrice;
    
    
    /**
     * Abstract - get Number of wheels
     * @return m_intNumberOfWheels
     */
    public int getM_intNumberOfWheels() {
        return m_intNumberOfWheels;
    }
    
    /**
     * Abstract - set Number of wheels
     * @param m_intNumberOfWheels
     */
    public void setM_intNumberOfWheels(int m_intNumberOfWheels) {
        if (m_intNumberOfWheels < 0) {
            m_intNumberOfWheels = 0;
        } else if (m_intNumberOfWheels > 4) {
            m_intNumberOfWheels = 4;
        }
        this.m_intNumberOfWheels = m_intNumberOfWheels;
    }
    
    /**
     * Abstract - get Number of MPG
     * @return m_intNumberOfMPG
     */
    public int getM_intNumberOfMPG() {
        return m_intNumberOfMPG;
    }
    
    /**
     * Abstract - set number of MPG
     * @param m_intNumberOfMPG
     */
    public void setM_intNumberOfMPG(int m_intNumberOfMPG) {
        if (m_intNumberOfMPG < 0) {
            m_intNumberOfMPG = 0;
        }
        this.m_intNumberOfMPG = m_intNumberOfMPG;
    }

    /**
     * Abstract - get Price
     * @return m_dblPrice
     */
    public float getM_dblPrice() {
        return m_dblPrice;
    }

    /**
     * Abstract - set Price
     * @param m_dblPrice
     */
    public void setM_dblPrice(float m_dblPrice) {
        if (m_dblPrice < 0) {
            m_dblPrice = 0;
        }
        this.m_dblPrice = m_dblPrice;
    }
    
    
    /**
     * Abstract - get Type 
     * @return m_strType
     */
    public String getM_strType() {
		return m_strType;
	}
    
    /**
     * Abstract - set Type
     * @param m_strType
     */
	public void setM_strType(String m_strType) {
		int intLength = 0;
		intLength = m_strType.length();
		if ( intLength > 50 ) {
			m_strType = m_strType.substring( 0, intLength );
			System.out.println("Type cannot exceed 50 characters in length");
		}
		this.m_strType = m_strType;
	}

	/**
	 * Abstract - get how to drive 
	 */
	public void getHowToDrive() {
    	System.out.print( "undifined" );
    }
}