package uk.ac.glasgow.internman.impl.adManager;

import uk.ac.glasgow.internman.*;
import java.util.Map;

/**
 * Called Interact in System Architecture
 * @author 1002536r
 *
 */
public interface AdManagerInterface {
	
	public Advertisement createNewAdvertisement(Employer e, String applicationDetails);
	
	public Advertisement selectAdvertisement(Integer index);
	
	public void reviseAdvertisement(Integer index, Advertisement revise);
	
	public Map<Integer, Advertisement> getAdvertisements();
	
	public void publishAdvertisement(Integer adIndex);

}
