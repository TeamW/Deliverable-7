package uk.ac.glasgow.internman.impl.adManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.impl.AdvertisementImpl;

public class AdManager implements AdManagerInterface {

	private boolean advertDatabaseLoaded;
	private static final String advertDatabaseLocation = System
			.getProperty("user.dir") + "/advert.database";
	private File advertDatabase;
	private HashMap<Integer, Advertisement> adverts;
	private Integer maxAdvertId;
	public static final AdManager AM = new AdManager();

	private AdManager() {
		advertDatabaseLoaded = loadAdvertDatabase();
		maxAdvertId = getMaxAdvertId();
	}

	public static AdManager getInstance() {
		return AM;
	}

	@Override
	public Advertisement createNewAdvertisement(Employer e,
			String applicationDetails) {
		Advertisement newAdvert = new AdvertisementImpl(e, applicationDetails);
		maxAdvertId = new Integer(maxAdvertId.intValue() + 1);
		adverts.put(maxAdvertId, newAdvert);
		updateAdvertDatabase();
		return newAdvert;

	}

	@Override
	public Advertisement selectAdvertisement(Integer index) {
		return adverts.get(index);

	}

	@Override
	public void reviseAdvertisement(Integer index, Advertisement revise) {
		adverts.put(index, revise);

	}

	@Override
	public Map<Integer, Advertisement> getAdvertisements() {
		return adverts;
	}

	@Override
	public void publishAdvertisement(Integer adIndex, String comment) {
		Advertisement temp = adverts.get(adIndex);
		/* do we store comments? */
		temp.PublishAd();
		updateAdvertDatabase();

	}

	@SuppressWarnings("unchecked")
	private boolean loadAdvertDatabase() {
		if (advertDatabaseLoaded) {
			return true;
		}
		ObjectInputStream input = null;
		advertDatabase = new File(advertDatabaseLocation);
		if (advertDatabase.exists()) {
			try {
				input = new ObjectInputStream(new FileInputStream(
						advertDatabase));
				adverts = (HashMap<Integer, Advertisement>) (input.readObject());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out
					.println("Creating employer database, okay on first run.");
			adverts = new HashMap<Integer, Advertisement>();
			ObjectOutputStream oos = null;
			try {
				advertDatabase = new File(advertDatabaseLocation);
				FileOutputStream fos = new FileOutputStream(advertDatabase);
				oos = new ObjectOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				oos.writeObject(adverts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean updateAdvertDatabase() {
		if (!advertDatabaseLoaded) {
			return false;
		}
		ObjectOutputStream oos = null;
		try {
			advertDatabase = new File(advertDatabaseLocation);
			FileOutputStream fos = new FileOutputStream(advertDatabase);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			oos.writeObject(adverts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private Integer getMaxAdvertId() {
		if (!advertDatabaseLoaded) {
			advertDatabaseLoaded = loadAdvertDatabase();
		}
		int value = 0;
		for (Integer i : adverts.keySet()) {
			if (i.intValue() > value) {
				value = i.intValue();
			}
		}
		return new Integer(value);
	}

}
