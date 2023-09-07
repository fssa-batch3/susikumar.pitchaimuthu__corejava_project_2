package com.fssa.freshnest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fssa.freshnest.constants.StillConstants;
import com.fssa.freshnest.model.Still;
import com.fssa.freshnest.model.User;
import com.fssa.freshnest.services.exceptions.ServiceException;

/**
 * This class contains test cases for the StillService class, which handles
 * various still-related operations.
 *
 * @author SusikumarPitchaimuth
 */
class TestStillService {

	// Test still Creation feature

	@Test
	void testStillCreateSuccess() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		User user = new User();
		user.setUserId(1);

		Still still = new Still("https://www.example.com", user, "Supreme", currentDate, currentTime, false, false);
		StillService stillService = new StillService();

		try {
			assertTrue(stillService.takeStill(still));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();

		}
	}

	@Test
	void testStillCreateWithInvalidStillUrl() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		User user = new User();
		user.setUserId(1);

		Still still = new Still("www.sampleImage.com", user, "Supreme", currentDate, currentTime, false, false);
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.takeStill(still));

		assertEquals(StillConstants.getInvalidStillUrlMessage(), result.getMessage());

	}

	@Test
	void testStillCreateWithInvalidStillName() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		User user = new User();
		user.setUserId(1);
		Still still = new Still("https://www.example.com", user, " ", currentDate, currentTime, false, false);
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.takeStill(still));

		assertEquals(StillConstants.getInvalidStillNameMessage(), result.getMessage());
	}

	// Test still list feature

	// test the still read success
	@Test
	void testStillListSuccess() {

		User user = new User();
		user.setUserId(1);

		Still still = new Still(user);
		StillService stillService = new StillService();
		try {
			List<Still> result = stillService.listStills(still);

			assertNotNull(result);
			assertFalse(result.isEmpty());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testStillListFailWithInvalidUserId() {

		User user = new User();
		user.setUserId(0);

		Still still = new Still(user);
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> {
			stillService.listStills(still);
		});

		assertEquals(StillConstants.getInvalidStillReadMessage(), result.getMessage());

	}

	// test Read the still details

	@Test
	void testStillDetailsReadFeatureSuccess() {
		Still still = new Still();
		still.setStillId(1);

		StillService stillService = new StillService();
		try {

			Still stillDetail = stillService.readStill(still);
			assertNotNull(stillDetail);

		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	// test the still invalid still id read
	@Test
	void testInvalidStillIdListFeature() {
		User user = new User();
		user.setUserId(-1);

		Still still = new Still(user);
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.readStill(still));
		assertEquals(StillConstants.getInvalidStillReadMessage(), result.getMessage());

	}

	// Test still update feature

	// test image update details
	@Test
	void testStillUpdateSuccess() {

		int parent_id = 1;
		// Adding that image is updated
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		User user = new User();
		user.setUserId(1);

		new Still(parent_id);
		// Adding the new image to the database
		Still still2 = new Still("https://www.example.com", user, "Supreme", currentDate, currentTime, false, false);

		StillService stillService = new StillService();
		try {
			assertTrue(stillService.updateStill(still2));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testImageUpdateWithInvalidImageUrlFailure() {
		int parent_id = 2;

		// Adding that image is updated

		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();

		User user = new User();
		user.setUserId(1);

		// Adding the new image to the database
		new Still(parent_id);
		Still still2 = new Still("www.example.png", user, "Supreme", currentDate, currentTime, false, false);

		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.updateStill(still2));

		assertEquals(StillConstants.getInvalidStillUrlMessage(), result.getMessage());
	}

	// Test still delete feature

	@Test
	void testStillDeleteSuccess() {
		int still_id = 2;
		User user = new User();
		user.setUserId(1);

		Still still = new Still(true, still_id, user);

		StillService stillService = new StillService();

		try {
			assertTrue(stillService.deleteStill(still));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	// test still delete with invalid details
	@Test
	void testStillDeleteDetailsWithInvalidStillId() {

		int still_id = -1;

		User user = new User();
		user.setUserId(1);

		StillService stillService = new StillService();
		Still still = new Still(true, still_id, user);
		ServiceException result = assertThrows(ServiceException.class, () -> stillService.deleteStill(still));

		assertEquals(StillConstants.getInvalidStillIdMessage(), result.getMessage());
	}

	// Test still favourite feature

	@Test
	void testImageFavouriteSuccess() {
		Still still = new Still(true, 1);
		StillService stillService = new StillService();

		try {
			assertTrue(stillService.favouriteStill(still));

		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testImageFavouriteNullDetails() {
		Still still = null;
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.favouriteStill(still));
		assertEquals(StillConstants.getInvalidStillFavouriteMessage(), result.getMessage());
	}

	@Test
	void testFilterStillsByTwoDates() {
		LocalDate from = LocalDate.of(2023, 06, 06);
		LocalDate to = LocalDate.of(2023, 10, 01);
		User user = new User();
		user.setUserId(1);
		Still still = new Still(from, to, user);
		StillService stillService = new StillService();

		try {
			List<Still> filteredStills = stillService.filterStills(still);
			assertNotNull(filteredStills);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testFilterStillsByTheInvalidTwoDate() {
		LocalDate from = LocalDate.of(2023, 04, 06);
		LocalDate to = LocalDate.of(2023, 05, 01);
		User user = new User();
		user.setUserId(2);
		Still still = new Still(from, to, user);
		StillService stillService = new StillService();

		ServiceException result = assertThrows(ServiceException.class, () -> stillService.filterStills(still));
		assertEquals(StillConstants.getInvalidStillFilterMessage(), result.getMessage());

	}

}
