package javacode;

/**
 * This class to store monthly report got by DataController from database
 * Contains setters-getters methods.
 * @author Denis Rozit
 *
 */
public class MonthReport {
	/** initiates variable monthReport (array of arrays)*/
	private String [] [] monthReport= {{"", "", "", ""}};
	
	/**
	 * @param monthReport String[][]
	 * 
	 */
    public void setMonthReport(String[][] monthReport) 
    {
        this.monthReport = monthReport;
    }
	
	/**
	 * @return monthReport
	 * 
	 */
    public String[][] getMonthReport() 
    {
        return monthReport;
    }
	
	
}
