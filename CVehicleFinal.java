import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
//import for sql Connection, DriverManager, ResultSet, Statement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.*;	// For connecting to SQL Server


/**
 * Vehicle Rental
 * 
 * @author Robert Firor
 * @version 1.0
 * @since 12/09/2025
 */
public class CVehicleFinal {
	//System.print.out.ln("Here are the pickup locations - we will call you with a location confirmation");
	/**
	 * This is the main method which is the point of entry Java
	 * @param args - Command line arguments (not used)
	 */

	//define the Connection
	private static Connection m_conAdministrator;
	//define the table, primary key, and column
	/**
	 * main method: intro to database processing
	 * This will connect to the db and display records on the console
	 */
	public static void main(String[] args) {
		try {
        	
			// Can we connect to the database?
			if ( OpenDatabaseConnectionSQLServer( ) == true )
			{	
				// Yes, load the teams list
				//LoadListFromDatabase( "TEmployees", "intEmployeeID" , "strFirstName" );
				System.out.println("Here are the pickup locations - we will call you with a location confirmation");
				LoadListFromDatabase( "TLocations", "intID" , "strName", "strAddress", "strCity", "strZip" );
			}
			else
			{
				// No, warn the user ...
				System.out.println("Error loading the table");
			}
			
		System.out.println("Process Complete");
    }
        catch 	(Exception e) {
        	System.out.println("An I/O error occurred: " + e.getMessage());
    	}
		
		
		
		//Declare Variables 
	String strResponse = "";
	int intResponse = 0; 
	String strPhoneNumber = "";
	String strEmail = "";
	String strFirstName = "";
	String strLastName = "";
	String strPickupDate = "";
	int intNumberOfDays = 0;
	int intNumberOfVehicles = 0;
	int intTypeOfVehicle = 0;
	float dblTotalPricePerVehicle = 0;
	float dblTotalPriceforAllVehicles = 0;
	// Print header
	System.out.println( "Welcome to Input Validation!" );
	System.out.println( "-----------------------------------------------" );
	System.out.println( "Enter QUIT or -1 to QUIT when prompted." );
	System.out.println( );
	
	// Ask user for first name
			System.out.print( "First Name: " );
			strResponse = ReadStringFromUser();
			// Check for quit
			if( strResponse.toUpperCase().matches( "QUIT" ) )
			{
				ProgramEnded();
				return;
			}
			// Quit not entered
			strFirstName = strResponse;
			
			// Ask user for last name
			System.out.print( "Last Name: " );
			strResponse = ReadStringFromUser();
			// Check for quit
			if( strResponse.toUpperCase().matches( "QUIT" ) )
			{
				ProgramEnded();
				return;
			}
			// Quit not entered
			strLastName = strResponse;
			
			// Ask user for phone number and loop until correct format is entered
			do
			{
				System.out.print( "Phone number in the format '###-###-####' or '##########': " );
				strResponse = ReadStringFromUser();
				// Check for quit
				if( strResponse.toUpperCase().matches("QUIT") )
				{
					ProgramEnded();
					return;
				}
			}while( IsValidPhoneNumber( strResponse ) == false );
			
			// Phone number is in correct format
			strPhoneNumber = strResponse;
			
			// Ask user for email and loop until correct format is entered
			do
			{
				System.out.print( "Email: " );
				strResponse = ReadStringFromUser();
				// Check for quit
				if( strResponse.toUpperCase().matches("QUIT") )
				{
					ProgramEnded();
					return;
				}
			}while( IsValidEmailAddress( strResponse ) == false );
			
			// Email is in correct format
			strEmail = strResponse;

			// Ask user for pick-up date and loop until correct format is entered
			do
			{
				System.out.print( "Pick-up date in the format MM-DD-YYYY or MM/DD/YYYY: " );
				strResponse = ReadStringFromUser();
				// Check for quit
				if( strResponse.toUpperCase().matches("QUIT") )
				{
					ProgramEnded();
					return;
				}
			}while( IsValidDate( strResponse ) == false );
			
			// Date is in correct format
			strPickupDate = strResponse;
			
			// Ask user for number of days to rent
			do
			{
				System.out.print( "Number of days you are renting (Type -1 to QUIT): " );
				//strResponse = ReadStringFromUser();
				intResponse = ReadIntegerFromUser();
				// Check for quit
				if( intResponse == -1 )
				{
					ProgramEnded();
					return;
				}
			}while( intResponse < 0 );
			intNumberOfDays = intResponse;
			// Ask user for number of vehicles to rent
			do
			{
				System.out.print( "Number of vehiles you are renting between 1 and 3 (Type -1 to QUIT): " );
				intResponse = ReadIntegerFromUser();
				// Check for quit
				if( intResponse == -1 )
				{
					ProgramEnded();
					return;
				}
			}while( intResponse > 3 || intResponse < 1 );
			
			intNumberOfVehicles = intResponse;
			
			
			
			do
			{
				System.out.print( "Type of vehile you are renting. Type 1 for car, 2 for motorbike and 3 for Trailer (Type -1 to QUIT): " );
				intResponse = ReadIntegerFromUser();
				// Check for quit
				if( intResponse == -1 )
				{
					ProgramEnded();
					return;
				}
			}while( intResponse > 3 || intResponse < 1 );
			
			intTypeOfVehicle = intResponse;
			System.out.println( "" );
		
			
			System.out.println( "" );
			
			
			CCar clsCar = new CCar( 4, 30, 40 );
			CMotorbike clsMotorbike = new CMotorbike( 2, 60, 20);
			CTrailer clsTrailer = new CTrailer( 2, 0, 30);
			
	//Output
			System.out.printf( "Customer Name: %-1s %s\n", strFirstName, strLastName );
			System.out.printf( "Phone Number: %s\n", strPhoneNumber );
			System.out.printf( "Email: %s\n", strEmail );
			
			if ( intTypeOfVehicle == 1 ) {
				//System.out.print("Type of Vehicle: " + clsCar.getM_strType());
				System.out.printf("Type of Vehicle: %s%n", clsCar.getM_strType());
				System.out.print("How Vehicle Drives: "); clsCar.getHowToDrive();
				//System.out.print("Miles Per Gallon" + clsCar.getM_intNumberOfMPG() );
				System.out.printf("Miles Per Gallon: %d%n", clsCar.getM_intNumberOfMPG());
				//Total price for rental for one vehicle based on price and number of days rented 
				dblTotalPricePerVehicle = clsCar.getM_dblPrice() * intNumberOfDays;
				//System.out.print( "Total Rental for vehicle based on price" + dblTotalPricePerVehicle );
				System.out.printf("Total Rental for Vehicle Based on Price %.2f%n", dblTotalPricePerVehicle);
				// Total price for rental for all vehicles rented based on price and number of days rented 
				dblTotalPriceforAllVehicles = clsCar.getM_dblPrice() * intNumberOfVehicles * intNumberOfDays;
				System.out.printf("Total Rental Amount for All Vehicles %.2f%n", dblTotalPriceforAllVehicles);				
			}else if ( intTypeOfVehicle == 2 ) {
				System.out.printf("Type of Vehicle: %s%n", clsMotorbike.getM_strType());
				System.out.print("How Vehicle Drives: "); clsMotorbike.getHowToDrive();
				//System.out.print("Miles Per Gallon" + clsMotorbike.getM_intNumberOfMPG() );
				System.out.printf("Miles Per Gallon: %d%n", clsMotorbike.getM_intNumberOfMPG());
				//Total price for rental for one vehicle based on price and number of days rented 
				dblTotalPricePerVehicle = clsMotorbike.getM_dblPrice() * intNumberOfDays;
				//System.out.print( "Total Rental for vehicle based on price" + dblTotalPricePerVehicle );
				System.out.printf("Total Rental for Vehicle Based on Price %.2f%n", dblTotalPricePerVehicle);
				// Total price for rental for all vehicles rented based on price and number of days rented 
				dblTotalPriceforAllVehicles = clsMotorbike.getM_dblPrice() * intNumberOfVehicles * intNumberOfDays;
				System.out.printf("Total Rental Amount for All Vehicles %.2f%n", dblTotalPriceforAllVehicles);
			}else if ( intTypeOfVehicle == 3 ) {
				System.out.printf("Type of Vehicle: %s%n", clsTrailer.getM_strType());
				System.out.print("How Vehicle Drives: "); clsTrailer.getHowToDrive();
				//System.out.print("Miles Per Gallon" + clsTrailer.getM_intNumberOfMPG() );
				System.out.printf("Miles Per Gallon: %d%n", clsTrailer.getM_intNumberOfMPG());
				//Total price for rental for one vehicle based on price and number of days rented 
				dblTotalPricePerVehicle = clsTrailer.getM_dblPrice() * intNumberOfDays;
				//System.out.print( "Total Rental for vehicle based on price" + dblTotalPricePerVehicle );
				System.out.printf("Total Rental for Vehicle Based on Price %.2f%n", dblTotalPricePerVehicle);
				// Total price for rental for all vehicles rented based on price and number of days rented 
				dblTotalPriceforAllVehicles = clsTrailer.getM_dblPrice() * intNumberOfVehicles * intNumberOfDays;
				System.out.printf("Total Rental Amount for All Vehicles %.2f%n", dblTotalPriceforAllVehicles);
			}
			
			
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			/**
			 * Abstract - Load list from Database 
			 * @param strTable - Table 
			 * @param strPrimaryKeyColumn - Primary key Column
			 * @param strNameColumn - Name Column
			 * @param strAddressColumn - Address Column
			 * @param strCityColumn - City Column
			 * @param strZipColumn - Zip Column
			 * @return blnResult
			 */
			public static boolean LoadListFromDatabase( String strTable, String strPrimaryKeyColumn, String strNameColumn, String strAddressColumn, String strCityColumn, String strZipColumn ) {
				
				//set flag to false
				boolean blnResult = false;
				
				try
				{
					String strSelect = "";
					Statement sqlCommand = null;
					ResultSet rstTSource = null;
					int intID = 0;
					String strName = "";
					String strAddress = "";
					
					// Build the SQL string
					strSelect = "SELECT " + strPrimaryKeyColumn + ", " + strNameColumn + "," + strAddressColumn + ", " +
			                strCityColumn + ", " +
			                strZipColumn
								+ " FROM " + strTable
								+ " ORDER BY " + strNameColumn; 
							
					// Retrieve the all the records	
					sqlCommand = m_conAdministrator.createStatement( );
					rstTSource = sqlCommand.executeQuery( strSelect );
					// Loop through all the records
					while( rstTSource.next( ) )
					{
						 intID = rstTSource.getInt(strPrimaryKeyColumn);
			             strName = rstTSource.getString(strNameColumn);
			             strAddress = rstTSource.getString(strAddressColumn);
			            String strCity = rstTSource.getString(strCityColumn);
			            String strZip = rstTSource.getString(strZipColumn);
						//System.out.println(" ID: " + intID + " Name: " + strName + " Address: " + strAddress + " City: " + strCity + "Zip: " + strZip );
			            System.out.printf("ID: %d Name: %-10s Address: %-15s City: %-15s Zip: %s%n", intID, strName, strAddress, strCity, strZip);
					}
					// Clean up
					rstTSource.close( );
					sqlCommand.close( );
					// Success
					blnResult = true;
				}
				catch 	(Exception e) {
					System.out.println( "Error loading table" );
					System.out.println( "Error is " + e );
				}
				
				return blnResult;
				}

			/** 
			 * method name: OpenDatabaseConnectionMSAccess
			 * The opens the database connection	
			 * This requires the following drivers: Use UCanAccess, an open-source JDBC driver.
			 * Include the following jar files in your code:
			 *		ucanaccess-2.0.7.jar
			 *		jackcess-2.0.4.jar
			 *		commons-lang-2.6.jar
			 *		commons-logging-1.1.3.jar
			 *		hsqldb.jar
			 *	To include those files select "Project / Properties / Java Build Path"
			 *	from the menu.  Click on the "Libraries" tab.  Click "Add External JARs".
			 *	Browse to the above jar files, which should be in a directory in your
			 * 	project (e.g. JDBC-to-MSAccess).  Select all five files and click "Open".  Click "OK".
			 *	
			 * Be sure to add the drivers to your program by selecting Project >> Properties >> Java Build Path
			 * @return blnResult
			 */
			public static boolean OpenDatabaseConnectionMSAccess( )
			{
				boolean blnResult = false;
				
				try {
					String strConnectionString = "";
					
					// Server name/port, IP address/port or path for file based DB like MS Access
					// System.getProperty( "user.dir" ) => Current working directory from where
					// application was started
					strConnectionString = "jdbc:ucanaccess://" + System.getProperty( "user.dir" )
										+ "\\Database\\dbHCM.accdb";
					// Open a connection to the database
					m_conAdministrator = DriverManager.getConnection( strConnectionString );
					// Success
					blnResult = true;
				}
				catch 	(Exception e) {
					System.out.println( "Try again - error in OpenDB ");
					System.out.println( "Error is " + e );
				}
				return blnResult;
			}
			/**
			 * OpenDatabaseConnectionSQLServer - get SQL db connection
			 * @return blnResult
			 */
			public static boolean OpenDatabaseConnectionSQLServer( )
			{
				boolean blnResult = false;
				
				try
				{
					SQLServerDataSource sdsTeamsAndPlayers = new SQLServerDataSource( );
					//tg-comment out --sdsTeamsAndPlayers.setServerName( "localhost" ); // localhost or IP or server name
					sdsTeamsAndPlayers.setServerName( "localhost\\SQLExpress" ); // SQL Express version
					sdsTeamsAndPlayers.setPortNumber( 1433 );
					sdsTeamsAndPlayers.setDatabaseName( "dbVehicleRental" );
					
					// Login Type:
					
						// Windows Integrated
						//tg-comment out --sdsTeamsAndPlayers.setIntegratedSecurity( true );
						
						// OR
						
						// SQL Server
					     sdsTeamsAndPlayers.setUser( "sa" );
						 sdsTeamsAndPlayers.setPassword( "password1" );	// Empty string "" for blank password
					
					// Open a connection to the database
					m_conAdministrator = sdsTeamsAndPlayers.getConnection(  );
					
					// Success
					blnResult = true;
				}
				catch( Exception excError )
				{
					// Display Error Message
					System.out.println( "Cannot connect - error: " + excError );

					// Warn about SQL Server JDBC Drivers
					System.out.println( "Make sure download MS SQL Server JDBC Drivers");
				}
				
				return blnResult;
			}
			
			
			/**
			* Name: CloseDatabaseConnection
			* Abstract: Close the connection to the database
			* @return blnResult
			*/ 
			public static boolean CloseDatabaseConnection( )
			{
				boolean blnResult = false;
				
				try
				{
					// Is there a connection object?
					if( m_conAdministrator != null )
					{
						// Yes, close the connection if not closed already
						if( m_conAdministrator.isClosed( ) == false ) 
						{
							m_conAdministrator.close( );
							
							// Prevent JVM from crashing
							m_conAdministrator = null;
						}
					}
					// Success
					blnResult = true;
				}
				catch( Exception excError )
				{
					// Display Error Message
					System.out.println( excError );
				}
				
				return blnResult;
			}


	
	/**
	 * Method: ReadStringFromUser - Get input from user
	 * @return strBuffer
	 */
	public static String ReadStringFromUser( )
	{

		String strBuffer = "";

		try
		{
			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

			// Read a line from the user
			strBuffer = burInput.readLine( );
		}
		catch( Exception excError )
		{
			System.out.println( excError.toString( ) );
		}

		// Return string
		return strBuffer;
	}
	
	
	/**
	 * Method: ProgramEnded - When user enters "QUIT"
	 */
	private static void ProgramEnded() {
		System.out.println( "-----------------------------------------------" );
		System.out.println( "Program ended by user." );
		System.out.println( "Thank you for using CState's Vehicle Rental!" );
		System.out.println( "Please come again." );
	}
	
	
	/**
	 * Method: IsValidPhoneNumber - Check if phone number entered is in correct format
	 * @param strPhoneNumber
	 * Phone number entered by user
	 * @return blnIsValidPhoneNumber
	 */
	public static boolean IsValidPhoneNumber(String strPhoneNumber) {
		boolean blnIsValidPhoneNumber = false;
		
		try
		{
			// Declare variables
			String strStart = "^";
			String strStop = "$";
			String strDash = "\\-";
			String strPattern1 = "";
			String strPattern2 = "";
			
			// String patterns
			// ###-###-####
			strPattern1 = strStart + "\\d{3}" + strDash + "\\d{3}" + strDash + "\\d{4}" + strStop;
			// ##########
			strPattern2 = strStart + "\\d{10}" + strStop;
			
			// Does it match any of the formats?
			if( strPhoneNumber.matches( strPattern1 ) == true || 
				strPhoneNumber.matches( strPattern2 ) == true )
			{
				// Yes
				blnIsValidPhoneNumber = true;
			}
		}
		catch( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}
		// Return result
		return blnIsValidPhoneNumber;
	}
	
	
	/**
	 * Method: IsValidEmailAddress - Check if email entered is valid
	 * @param strResponse
	 * Email entered by user
	 * @return blnIsValidEmailAddress
	 */
	private static boolean IsValidEmailAddress(String strEmailAddress) {
		boolean blnIsValidEmailAddress = false;
		
		try
		{
			// Declare variables
			String strStart = "^";
			String strStop = "$";
			String strPattern = "";
			
			// Set string pattern
			strPattern = strStart + "[a-zA-Z][a-zA-Z0-9\\.\\-]*" + "@" + "[a-zA-Z][a-zA-Z0-9\\.\\-]*\\.[a-zA-Z]{2,6}" + strStop;
			
			// Does it match?
			if( strEmailAddress.matches( strPattern ) == true )
			{
				// Yes
				blnIsValidEmailAddress = true;
			}
			
		}
		catch( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}
		
		return blnIsValidEmailAddress;
	}
	
	
	/**
	 * Method: IsValidDate - Check if date entered is valid
	 * @param strResponse
	 * Date entered by user
	 * @return blnIsValidDate
	 */
	private static boolean IsValidDate(String strDate) {
		boolean blnIsValidDate = false;
		
		try
		{
			// Declare variables
			String strStart = "^";
			String strStop = "$";
			String strDash = "\\-";
			String strSlash = "\\/";
			String strPattern1 = "";
			String strPattern2 = "";
			
			// Set string pattern
			// MM-DD-YYYY
			strPattern1 = strStart + "\\d{2}" + strDash + "\\d{2}" + strDash + "\\d{4}" + strStop;
			// MM/DD/YYYY
			strPattern2 = strStart + "\\d{2}" + strSlash + "\\d{2}" + strSlash + "\\d{4}" + strStop;
			
			// Does it match?
			if( strDate.matches( strPattern1 ) == true ||
				strDate.matches( strPattern2 ) == true)
			{
				// Yes
				blnIsValidDate = true;
			}
			
		}
		catch( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}
		
		return blnIsValidDate;
	}
	
	
	/**
	 * Method ReadIntegerFromUser - Get user input
	 * @return intValue
	 */
	public static int ReadIntegerFromUser( )
	{

		int intValue = 0;

		try
		{
			String strBuffer = "";	

			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

			// Read a line from the user
			strBuffer = burInput.readLine( );
			
			// Convert from string to integer
			intValue = Integer.parseInt( strBuffer );
		}
		catch( Exception excError )
		{
			System.out.println( excError.toString( ) );
		}
		

		// Return integer value
		return intValue;
	}
	
	
	/**
	 * Method: IsInteger- checks if string is an integer
	 * @param strResponse
	 * String to check
	 * @return blnNumeric
	 */
	public static boolean IsInteger(String strResponse) {
		boolean blnNumeric = true;
		
		try
		{
			Integer.parseInt(strResponse);
		}
		catch( NumberFormatException e )
		{
			blnNumeric = false;
		}
		
		return blnNumeric;
	}
	
}


