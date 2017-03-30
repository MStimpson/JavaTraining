package ecss;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Services {

	public String getString(){
		return "This is a test";
	}
	public String getCurrentDate(){
		return(new SimpleDateFormat("MMMM d, yyyy").format(new Date()));
	}
}
