<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<%
    try {
        Random r = new Random();
        int i = r.nextInt(999);
        Thread.sleep(i);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<div class="home">
    <div id="splashimage">
        <img src="images/homepage_car.gif">
    </div>
    <div class="fuelprices">
        <h1>Latest Fuel Prices</h1>
        <ul>
            <li>Regular: $<s:property value="fuelPrices.regular" /></li>
            <li>Mid Grade: $<s:property value="fuelPrices.midgrade" /></li>
            <li>Premium: $<s:property value="fuelPrices.premium" /></li>
            <li>E85: $<s:property value="fuelPrices.e85" /></li>
            <li>Diesel: $<s:property value="fuelPrices.diesel" /></li>
        </ul>
    </div>
</div>

<%@ include file="footer.jsp" %>