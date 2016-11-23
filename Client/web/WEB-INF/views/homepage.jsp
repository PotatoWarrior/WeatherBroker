<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  </head>
  <body>
    <spring:form modelAttribute="city">
        <h3>Enter city name: </h3><spring:input path="name"/>
        <spring:button>Submit</spring:button>
    </spring:form>
  </body>
</html>
