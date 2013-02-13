package uk.ac.glasgow.internman.impl.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ApproveAcceptedOffer.class, LoginTest.class,
		NotifyAcceptedOffer.class, PublishAdvertisementTest.class,
		SubmitAdvertisementTest.class, ViewStudentDetail.class,
		ViewAdvertisementSummary.class, RegisterEmployer.class })
public class AllTests {
}
