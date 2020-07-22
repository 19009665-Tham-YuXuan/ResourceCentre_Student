import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList = new ArrayList<Camcorder>();
		chromebookList = new ArrayList<Chromebook>();
	}

	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Check if there is a valid Camcorder arraylist to add to", camcorderList);

		// Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());

		// Given an empty list, after adding 1, the size of the list is 1
		// The item just added is the same as the first item of the list
		assertEquals("Test that Camcorder arraylist size is 1", 1, camcorderList.size());
		assertSame("Test that the Camcorder is added", cc1, camcorderList.get(0));

		// Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}

	@Test
	public void addChromebookTest() { //done by Da Wei
		// fail("Not yet implemented");
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
		
		// Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Test if that Chromebook arraylist size is 1?", 1, chromebookList.size());

		// The item just added is as same as the first item of the list
		assertEquals("Test that Chromebook arraylist size is 1", 1, chromebookList.size());
		assertSame("Test that Chromebook is added same as 1st item of the list?", cb1, chromebookList.get(0));

		// Add another item. test The size of the list is 2?
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that Chromebook arraylist size is 2?", 2, chromebookList.size());
	}

	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);

		// test if the list of camcorders retrieved from the SourceCentre is empty
		String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		String testOutput = "";
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());

		// test if the expected output string same as the list of camcorders retrieved
		// from the SourceCentre
		allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

	}

	@Test
	public void retrieveAllChromebookTest() { //done by Yu Xuan
		// fail("Not yet implemented");
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);

		// test if the list of chromebook retrieved from the SourceCentre is empty
		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Chromebook arraylist size is 2?", 2, chromebookList.size());

		// test if the expected output string same as the list of Chromebook retrieved
		// from the SourceCentre
		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "", "Mac OS");
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "SAMSUNG Chromebook 4+", "Yes", "", "Win 10");
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);
	}

	@Test
	public void doLoanCamcorderTest() { //done by Vivian
		// fail("Not yet implemented");
		// Test that Camcorder list is not null, so that item can be loaned
		assertNotNull("Test if there is valid Camcorder arraylist to loan items from", camcorderList);

		//Test if a Camcorder that is available can be loaned
		LocalDate acceptedDueDate = LocalDate.now().plusDays(1);
		boolean condition = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", acceptedDueDate.toString());
		assertFalse("Test an available Camcorder can be loaned", condition);
		
		//Test if a Camcorder that has a due date before the current date cannot be loaned
		LocalDate rejectedDueDate = LocalDate.now().minusDays(1);
		condition = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", rejectedDueDate.toString());
		assertFalse("Test that a Camcorder that has a due date before the current date cannot be loaned", condition);
		
		//Test that a Camcorder that has a due date on the current date cannot be loaned
		LocalDate currentDate = LocalDate.now();
		condition = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", currentDate.toString());
		assertFalse("Test that a Camcorder that has a due date before the current date cannot be loaned", condition);
		
		//Test that a Camcorder that is unavailable cannot be loaned
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		condition = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", acceptedDueDate.toString());
		assertFalse("Test that an unavailable Camcorder cannot be loaned", condition);
		
		//Test that a Camcorder that is not in the list cannot be loaned
		condition = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", acceptedDueDate.toString());
		assertFalse("Test that a Camcorder that is not on the list cannot be loaned", condition);
	}

	@Test
	public void doLoanChromebookTest() { //done by Vivian
		// fail("Not yet implemented");
		// Test that Chromebook list is not null, so that item can be loaned
		assertNotNull("Test if there is valid Chromebook arraylist to loan items from", chromebookList);

		//Test if a Chromebook that is available item can be loaned
		LocalDate acceptedDueDate = LocalDate.now().plusDays(1);
		boolean condition = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", acceptedDueDate.toString());
		assertFalse("Test an available Chromebook can be loaned", condition);
		
		//Test if a Chromebook that has a due date before the current date cannot be loaned
		LocalDate rejectedDueDate = LocalDate.now().minusDays(1);
		condition = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", rejectedDueDate.toString());
		assertFalse("Test that a Chromebook that has a due date before the current date cannot be loaned", condition);
		
		//Test that a Chromebook that has a due date on the current date cannot be loaned
		LocalDate currentDate = LocalDate.now();
		condition = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", currentDate.toString());
		assertFalse("Test that a Chromebook that has a due date before the current date cannot be loaned", condition);
		
		//Test that a Chromebook that is unavailable cannot be loaned
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);
		condition = ResourceCentre.doLoanChromebook(chromebookList, "CB0012", acceptedDueDate.toString());
		assertFalse("Test that an unavailable Chromebook cannot be loaned", condition);
		
		//Test that a Chromebook that is not in the list cannot be loaned
		condition = ResourceCentre.doLoanChromebook(chromebookList, "CC0013", acceptedDueDate.toString());
		assertFalse("Test that a Chromebook that is not on the list cannot be loaned", condition);
		
	}

	@Test
	public void doReturnCamcorderTest() { // done by Vivian
		// fail("Not yet implemented");
		// write your code here
		//Test that Camcorder list is not null, so that item can be loaned
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertNotNull("Test if there is valid Camcorder list to add to", camcorderList);
		
		//Test if Camcorder that is available cannot be returned
		boolean condition = ResourceCentre.doReturnCamcorder(camcorderList,"CC0011");
		assertFalse("Test if Camcorder that is available cannot be returned", condition);
		
		//Test if loaned out Camcorder can be returned
		cc2.setIsAvailable(false);
		condition = ResourceCentre.doReturnCamcorder(camcorderList,"CC0012");
		assertTrue("Test if if loaned out Camcorder can be returned", condition);
		
		//Test that a Camcorder that is not in the list cannot be loaned
		condition = ResourceCentre.doReturnCamcorder(camcorderList,"CC0013");
		assertFalse("Test that a Camcorder that is not in the list cannot be loaned", condition);
	}

	@Test
	public void doReturnChromebookTest() { //done by Vivian
		// fail("Not yet implemented");
		// write your code here
		//Test that Chromebook list is not null, so that item can be loaned
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertNotNull("Test if there is valid Chromebook list to add to", chromebookList);
		
		//Test if Chromebook that is available cannot be returned
		boolean condition = ResourceCentre.doReturnChromebook(chromebookList,"CB0011");
		assertFalse("Test if Chromebook that is available cannot be returned", condition);
		
		//Test if loaned out Chromebook can be returned
		cb2.setIsAvailable(false);
		condition = ResourceCentre.doReturnChromebook(chromebookList,"CB0012");
		assertTrue("Test if if loaned out Chromebook can be returned", condition);
		
		//Test that a Chromebook that is not in the list cannot be loaned
		condition = ResourceCentre.doReturnChromebook(chromebookList,"CB0013");
		assertFalse("Test that a Chromebook that is not in the list cannot be loaned", condition);
	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
