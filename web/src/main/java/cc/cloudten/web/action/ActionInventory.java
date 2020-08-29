 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cc.cloudten.web.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.opensymphony.xwork2.ActionSupport;

import cc.cloudten.supercar.externaldata.FuelPrices;
import cc.cloudten.supercar.util.HttpHelper;
import cc.cloudten.supercar.utils.PropertiesHelper;

import com.google.gson.Gson;

import cc.cloudten.supercar.models.Car;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionInventory extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(ActionInventory.class);

	private Car[] cars;
	
	public String execute() throws Exception {
		log.info("ActionInventory executing");

		String apiURL = PropertiesHelper.getWebServiceProps().getProperty("api.url");

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(apiURL + "/inventory/");
			
		HttpResponse res = client.execute(req);
		
		if (res.getStatusLine().getStatusCode() == 200) {
			log.info("API returned ok");
			
			try {
				String resp = HttpHelper.getJsonFromHttpResponse(res);

				log.info("JSON Payload received by Backend Service:");
				log.info(resp);

				Gson gson = new Gson();
				cars = gson.fromJson(resp, Car[].class);

			} catch (Throwable e) {
				throw new Exception("Error parsing API response", e);
			}
		
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public Car[] getCars() {
		return cars;
	}
}
