package com.vapronixweb.holaquiz.scripts;

import org.testng.annotations.Test;

import com.vapronixweb.generic.BaseLib;

public class DashboardTest extends BaseLib {
	
	@Test(groups = "Regression")
	public void TC_001_VerifyHeaderTest() {
		dashboardPage.verifyAllHeaderElements();
		
	}

}
