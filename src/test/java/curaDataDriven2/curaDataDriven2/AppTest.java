package curaDataDriven2.curaDataDriven2;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

public class AppTest {
	App obj = new App();
  @Test
  public void f() throws IOException {
	  obj.launchWebSite();
	  obj.login();
	  
  }
}
