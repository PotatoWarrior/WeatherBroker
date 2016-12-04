<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
  </head>
  <body>
  <fmt:setLocale value="en_US" />
  <h3>Forecast list:</h3>
  <ul>
      <c:forEach items="${forecastList}" var = "forecast">
          <li>
              <ul style="list-style-type:square">
                  <li>City: ${forecast.city}</li>
                  <li>Country: ${forecast.country}</li>
                  <li>Region: ${forecast.region}</li>
                  <li>Date: ${forecast.dateAsString} </li>
                  <li>High: ${forecast.high}</li>
                  <li>Low: ${forecast.low}</li>
              </ul>
          </li>
      </c:forEach>
  </ul>
  <spring:form modelAttribute="city">
      <h3>Add forecast for city: </h3><spring:input path="name"/>
      <spring:button>Add</spring:button>
  </spring:form>
  </body>
</html>
