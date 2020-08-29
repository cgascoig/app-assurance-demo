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

<ul>
  <s:iterator value="cars">
    <li><s:property value="model" /></li>
  </s:iterator>
</ul>
<table>
<s:iterator value="cars">
  <tr> 
    <td > 
    <p><a href="cars.do?query=manu&mid=<s:property />" ><s:property /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  	</td>
  	<td><p><a href="cars.do?query=manu&mid=<s:property />"><img src="images/manufacturers/<s:property />"></a></p></td>
  </tr>
</s:iterator>

</table>
<%@ include file="footer.jsp" %>