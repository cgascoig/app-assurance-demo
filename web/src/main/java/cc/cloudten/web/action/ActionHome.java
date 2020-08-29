package cc.cloudten.web.action;

import com.opensymphony.xwork2.ActionSupport;
import cc.cloudten.supercar.externaldata.FuelPrices;


public class ActionHome extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String fuelPrice;
	private FuelPrices fuelPrices;
	
	public String execute() throws Exception {
		fuelPrice = "999";
		fuelPrices = FuelPrices.getFuelPrices();
		
		return SUCCESS;
	}
	
	public String getFuelPrice() {
		return fuelPrice;
	}
	
	public FuelPrices getFuelPrices() {
		return fuelPrices;
	}
}
