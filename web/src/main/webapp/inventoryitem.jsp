<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>

<div class="inventoryitem">
    <h1><img src="images/manufacturers/<s:property value="car.mfg_smallLogo"/>" /><s:property value="car.model" /></h1>
    <h2><s:property value="car.year" /> <s:property value="car.mfg_name" /> $<s:property value="car.price" /></h2>
    <p>
      <img src="images/cars/<s:property value="car.photo"/>.jpg" />
    </p>
    <pre>
 <s:property value="car.summary" escapeHtml="true" />
    </pre>
</div>

<%@ include file="footer.jsp" %>