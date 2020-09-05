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
	
	public static int[] buffer = new int[10];
	public static int i_bufferSize = 10;

	/**
	 * 
	 */
	public InventoryServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUri = request.getRequestURI();
		log.info("Inventory Service Recieved Request URI: " + reqUri);

		String carId = request.getParameter("carId");
		String bufferSize = request.getParameter("bufferSize");

		if (bufferSize != null) {
			int newBufferSize = Integer.parseInt(bufferSize);
			log.info("Resizing buffer to " + newBufferSize);

			if (newBufferSize > 0) {
				buffer = new int[newBufferSize];
				log.info("Resized buffer to " + newBufferSize);

				i_bufferSize = newBufferSize;
			}
		}

		log.info("Filling buffer with data");
		for (int i=0; i< i_bufferSize; i++) {
			buffer[i] = i % 256;
		}
		log.info("Finished filling buffer with data");

		Gson gson = new Gson();

		if (carId == null) {
			// Get all cars

			Collection<Car> cars = Car.getCars();
			
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(gson.toJson(cars));
		} else {
			// Get one car
			Car car = Car.getCar(carId);

			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(gson.toJson(car));
		}
	}
}