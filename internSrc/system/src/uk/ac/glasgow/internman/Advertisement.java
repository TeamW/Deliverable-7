package uk.ac.glasgow.internman;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface Advertisement extends Serializable {

	public enum AdvertisementStatus {
		PENDING, PUBLISHED
	}

	Map<Integer, Role> getRoles();

	Employer getEmployer();

	String getApplicationDetails();

	String getComments();

	AdvertisementStatus getStatus();
	
    void PublishAd();
	
	Role addNewRole(
			String title, String location, Date start, Date end, String description, Double salary);

}
