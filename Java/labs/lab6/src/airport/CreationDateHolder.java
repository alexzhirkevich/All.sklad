package airport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreationDateHolder {

	protected Date creationDate = new Date();

	protected SimpleDateFormat dateFormat = new SimpleDateFormat();

	CreationDateHolder(){}

	public String getCreationDateString(){
		return dateFormat.format(creationDate);
	}

	public Date getCreationDate() {
		return (Date)creationDate.clone();
	}
}
