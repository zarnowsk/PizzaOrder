<%-- 
    Document   : displayOrder
    Created on : 14-Sep-2019, 4:22:43 PM
    Author     : Michal Zarnowski
--%>

<%@page import="com.michzarnowski.michal_zarnowski_a1.model.PizzaOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% PizzaOrder pizzaOrder = (PizzaOrder)request.getAttribute("pizzaOrder");
    float price = (float) pizzaOrder.getPrice();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>

    </head>
    <body onload="displayCountDown()">

        <h1>
            Pizza Order for <%= session.getAttribute("name")%>, 
            <%= session.getAttribute("tel")%> total $<%=String.format("%.2f", price)%>
        </h1>
        <h2>
            Your pizza will be <% if (pizzaOrder.isDelivery()) {
                    out.print("delivered within 40 minutes");
                } else {
                    out.print("ready for pickup in 20 minutes");
                }
            %>
        </h2>
        <h3>
            <% out.print(getPizzaSize(pizzaOrder));%> pizza with
        </h3>
        <%  if (pizzaOrder.getToppings().length == 0) {
                out.print("<p>No Toppings</p>");
            } else { %>
        <ul>
            <% for (int i = 0; i < pizzaOrder.getToppings().length; i++) { %>
            <li><% out.print(pizzaOrder.getToppings()[i]); %></li>

            <% } %>
        </ul>
        <% }%>
        <br><h1>Pizza countdown:</h1>
        <p id="clock"></p>

        <script language="javascript">
            function displayCountDown() {

                // Set the time we're counting down to based on delivery type
                var countDownTime = new Date().getTime(); //current time
                <% int timeTillReady = 0;
                   if (pizzaOrder.isDelivery()) {
                       timeTillReady += 40;
                   } else {
                       timeTillReady += 20;
                   }
                   timeTillReady *= 60000;%> //ready time in millisecs
                countDownTime += <%= timeTillReady%>;

                // Update the count down every 1 second
                var update = setInterval(function () {

                    // Get current time
                    var now = new Date().getTime();

                    // Get amount of millisecods between now and countdown time
                    var remaining = countDownTime - now;

                    // Time calculations for minutes and seconds
                    var minutes = Math.floor((remaining % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((remaining % (1000 * 60)) / 1000);

                    // Display the result in the element with id="clock"
                    document.getElementById("clock").innerHTML = minutes + "m " +
                            seconds + "s ";

                }, 1000);
            }
            ;
        </script>
    </body>
</html>

<%!
    public String getPizzaSize(PizzaOrder pizzaOrder) {
        String pizzaSize = "";
        pizzaSize += pizzaOrder.getSize().substring(0, 1).toUpperCase();
        pizzaSize += pizzaOrder.getSize().substring(1);
        return pizzaSize;
    }
%>
