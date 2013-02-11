package uk.ac.glasgow.internman.impl.adManager;

import java.util.Map;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;

public class AdManager implements AdManagerInterface {

	public static final AdManager AM = new AdManager();
	
	public AdManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static AdManager getInstance(){
		return AM;
	}
	
	@Override
	public Advertisement createNewAdvertisement(Employer e, String applicationDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertisement selectAdvertisement(Integer index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reviseAdvertisement(Integer index, Advertisement revise) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Integer, Advertisement> getAdvertisements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void publishAdvertisement(Integer adIndex, String comment) {
		// TODO Auto-generated method stub

	}

}
