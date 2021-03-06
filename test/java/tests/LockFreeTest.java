package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lockfree.selfOrganizing.SelfOrgList;

public class LockFreeTest {

	SelfOrgList sol;
	Long start = (long) 0, end = (long) 0;
	int i = 0;
	static Long len = (long) 0;

	@BeforeTest
	protected void setUp() throws Exception {

		sol = new SelfOrgList();

		System.gc();

		for (int i = 0; i < 150; i++)
			sol.add(10);

		sol.add(7);
		start = System.nanoTime();
	}

	@AfterTest
	protected void tearDown() throws Exception {
		end = System.nanoTime();
		sol.list();
		double seconds = (end - start) / 1000000000.0;
		System.out.println("Time: " + seconds + "s Size: " + sol.size());

	}

	@Test(threadPoolSize = 5, invocationCount = 1000, timeOut = 10000)
	public void test2() {

		sol.add(10);

		// sol.remove(10);
		// sol.remove(10);
		// sol.search(7);

	}

}
