package cc.cloudten.api;

import java.io.InputStreamReader;
import java.util.Properties;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import cc.cloudten.supercar.utils.PropertiesHelper;
import cc.cloudten.supercar.util.HttpHelper;


/**
 * @author james
 *
 */
public class ApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888011897341720634L;
	private static Log log = LogFactory.getLog(ApiServlet.class);

	private String backendInventory;
	

	/**
	 * 
	 */
	public ApiServlet() {
		try {
			Properties props = PropertiesHelper.getApiServiceProps();

			backendInventory = props.getProperty("backend.inventory");
		} catch (Throwable e) {
			log.error("Error getting API properties: ", e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {			
			String reqUri = request.getRequestURI();

			log.info("API Service Recieved Request with URI: " + reqUri);

			String backendURL = "";

			if (reqUri.startsWith("/api/inventory/")) {
				log.info("Sending request to inventory service");
				backendURL = backendInventory;
			} else {
				log.info("Failed to match backend service");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println("Invalid request");
				return;
			}

			log.info("Sending request to backend URL: " + backendURL);

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet req = new HttpGet(backendURL);
				
			HttpResponse res = client.execute(req);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				log.info("Backend service returned ok");
				
				String resp = HttpHelper.getJsonFromHttpResponse(res);
				
				log.info("JSON Payload received by Backend Service " + request.getRequestURI());
				log.info(resp);
				
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(resp);
				
			} else {
				log.info("ERROR: Inventory Service returned " + res.getStatusLine().getStatusCode());
			}

			
		} catch (Throwable ex) {
			log.info("########################## API Service Failure ##########################");
			log.error("########################## " + ex.getMessage() + " ##########################", ex);
			ex.printStackTrace();
				
		}
		

	}


    
}
