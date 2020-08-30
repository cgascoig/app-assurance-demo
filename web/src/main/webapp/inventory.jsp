<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<p class="normal"></p>
<!-- img src="images/line.gif" -->

<%
//try {
//			Random r = new Random();
//			int i = r.nextInt(999);
//			Thread.sleep(i);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
%>

<div id="inventorylist">
  <s:iterator value="cars">
    <div class="inventoryitem">
      <h1><img src="images/manufacturers/<s:property value="mfg_smallLogo"/>" /> <a href="<s:url action="inventoryitem"><s:param name="carId" value="%{carId}" /></s:url>"><s:property value="model" /></a></h1>
      <h2><s:property value="year" /> <s:property value="mfg_name" /> $<s:property value="price" /></h2>
      <p>
        <img src="images/cars/<s:property value="photo"/>.jpg" />
      </p>
    </div>
  </s:iterator>
</div>

<%@ include file="footer.jsp" %>