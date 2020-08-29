package cc.cloudten.inventory;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

import cc.cloudten.supercar.models.Car;

/**
 * @author james
 *
 */
public class InventoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888011897341720634L;
	private static Log log = LogFactory.getLog(InventoryServlet.class);
	

	/**
	 * 
	 */
	public InventoryServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUri = request.getRequestURI();
		
		log.info("Inventory Service Recieved Request URI: " + reqUri);

		Collection<Car> cars = Car.getCars();

		Gson gson = new Gson();
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(gson.toJson(cars));
	}
}