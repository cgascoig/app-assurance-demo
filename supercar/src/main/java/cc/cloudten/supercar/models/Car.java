package cc.cloudten.supercar.models;

import java.util.Collection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cc.cloudten.supercar.db.Database;


public class Car {
    private static Log log = LogFactory.getLog(Car.class);
    
    public int carId;
	public String name;
	public String model;
	public String manufacturer;
	public String colour;
	public int year;
	public int price;
	public String summary;
	public String description;
	public int wheelSize;
	public int tyreSize;
	public boolean isManual;
	public String photo;
	
	public String mfg_name;
	public String mfg_web;
	public String mfg_email;
	public String mfg_smallLogo;
	public String mfg_largeLogo;

	public static Collection<Car> getCars() {

		Statement statement = null;
		ResultSet resultSet = null;
        
        Car car = null;
        List cars = new ArrayList();
        
        try (Connection connection = Database.getConnection()) {
            // String sql = "SELECT MANUFACTURER_ID, NAME, WEB, EMAIL, SMLLOGO, LRGLOGO FROM MANUFACTURER ORDER BY NAME";
            String sql = "SELECT CAR_ID, CARS.NAME AS CAR_NAME, MANUFACTURER.NAME AS MANUFACTURER_NAME, MODEL, SUMMARY, DESCRIPTION, CARS.MANUFACTURER_ID AS MANUFACTURER_ID, COLOUR, YEAR, PRICE, PHOTO, WEB, EMAIL, SMLLOGO, LRGLOGO FROM CARS INNER JOIN MANUFACTURER ON CARS.MANUFACTURER_ID = MANUFACTURER.MANUFACTURER_ID";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                car = new Car();

                car.carId = resultSet.getInt("CAR_ID");
                car.name = resultSet.getString("CAR_NAME");
                car.model = resultSet.getString("MODEL");
                car.summary = resultSet.getString("SUMMARY");
                car.description = resultSet.getString("DESCRIPTION");
                car.manufacturer = resultSet.getString("MANUFACTURER_ID");
                car.colour = resultSet.getString("COLOUR");
                car.year = resultSet.getInt("YEAR");
                car.price = resultSet.getInt("PRICE");
                car.photo = resultSet.getString("PHOTO");

				
                car.mfg_name = resultSet.getString("MANUFACTURER_NAME"); 
                car.mfg_web = resultSet.getString("WEB");
                car.mfg_email = resultSet.getString("EMAIL");
                car.mfg_smallLogo = resultSet.getString("SMLLOGO");
				car.mfg_largeLogo = resultSet.getString("LRGLOGO");
				
                cars.add(car);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getManufacturers", ex);
        }
        
        return cars;
    }

    public static Car getCar(String carIdStr) {
        int carId = Integer.parseInt(carIdStr);

        Statement statement = null;
		ResultSet resultSet = null;
        
        Car car = null;
        
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT CAR_ID, CARS.NAME AS CAR_NAME, MANUFACTURER.NAME AS MANUFACTURER_NAME, MODEL, SUMMARY, DESCRIPTION, CARS.MANUFACTURER_ID AS MANUFACTURER_ID, COLOUR, YEAR, PRICE, PHOTO, WEB, EMAIL, SMLLOGO, LRGLOGO FROM CARS INNER JOIN MANUFACTURER ON CARS.MANUFACTURER_ID = MANUFACTURER.MANUFACTURER_ID WHERE CARS.CAR_ID = " + carId;
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                car = new Car();

                car.carId = resultSet.getInt("CAR_ID");
                car.name = resultSet.getString("CAR_NAME");
                car.model = resultSet.getString("MODEL");
                car.summary = resultSet.getString("SUMMARY");
                car.description = resultSet.getString("DESCRIPTION");
                car.manufacturer = resultSet.getString("MANUFACTURER_ID");
                car.colour = resultSet.getString("COLOUR");
                car.year = resultSet.getInt("YEAR");
                car.price = resultSet.getInt("PRICE");
                car.photo = resultSet.getString("PHOTO");

				
                car.mfg_name = resultSet.getString("MANUFACTURER_NAME"); 
                car.mfg_web = resultSet.getString("WEB");
                car.mfg_email = resultSet.getString("EMAIL");
                car.mfg_smallLogo = resultSet.getString("SMLLOGO");
				car.mfg_largeLogo = resultSet.getString("LRGLOGO");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getManufacturers", ex);
        }
        
        return car;
    }
}
