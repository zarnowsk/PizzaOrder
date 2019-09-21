<%-- 
    Document   : displayOrder
    Created on : 14-Sep-2019, 4:22:43 PM
    Author     : Michal Zarnowski
--%>

<%@page import="com.michzarnowski.michal_zarnowski_a1.model.PizzaOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    //Get the pizza order object from request
    PizzaOrder pizzaOrder = (PizzaOrder)request.getAttribute("pizzaOrder");
    
    //Get pizza price from pizza object
    float price = (float) pizzaOrder.getPrice();
    
    //Initial string for gif path
    String gifSrc = "";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
        <link href='https://fonts.googleapis.com/css?family=Lobster&display=swap' rel='stylesheet'>
        <link rel='stylesheet' type='text/css' href='css/main.css' />
    </head>
    <!-- Start countdown function once page is loaded -->
    <body onload="displayCountDown()">

        <!-- Name, tel number and price -->
        <h1>
            Pizza Order for <%= session.getAttribute("name")%>, 
            <%= session.getAttribute("tel")%> total $<%= String.format("%.2f", price)%>
        </h1>
        
        <!-- Delivery time, method + address -->
        <h2>
            Your pizza will be 
            <% 
                //Specify gif path and print ready time and address based on
                //delivery method
                if (pizzaOrder.isDelivery()) {
                gifSrc = "media/deliveryGif.webp";    
                
                out.print("delivered within 40 minutes to:<br>\n");
                String[] deliveryDetails = (String[])request.getAttribute("deliveryDetails");
                for(String detail : deliveryDetails) {
                    out.println(detail + "<br>");
                }
            } else {
                gifSrc = "media/pickUpGif.webp";
                out.print("ready for pickup in 20 minutes at:<br>\n");
                out.println("99 Pizza Street<br>");
                out.println("Pizzaville<br>");
                out.println("P1Z2Z4<br>");
            }
            %>
        </h2>
        
        <!-- Pizza size -->
        <h3>
            <% out.print(getPizzaSize(pizzaOrder));%> pizza with
        </h3>
        
        <!-- Toppings -->
        <%  if (pizzaOrder.getToppings().length == 0) {
                out.print("<p>No Toppings</p>");
            } else { %>
        <div id='list'>
            <ul>
                <% for (int i = 0; i < pizzaOrder.getToppings().length; i++) { %>
                <li><% out.print(pizzaOrder.getToppings()[i]); %></li>
                
                <% } %>
            </ul>
        </div>
                <% }%>
                
        <!-- Pizza countdown -->
        <br>
        <h1>Pizza countdown:</h1>
        <p id="clock"></p>
        <iframe id="gifOne" src="<%= gifSrc %>" scrolling="no" frameBorder="0" width="25%" height="25%"></iframe>
        <iframe id="gifTwo" src="<%= gifSrc %>" scrolling="no" frameBorder="0" width="25%" height="25%"></iframe>

        <!-- JS for pizza countdown -->
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
            };
        </script>
    </body>
</html>

<%!
    /**
     * Method capitalizes first letter of the pizza size
     */
    public String getPizzaSize(PizzaOrder pizzaOrder) {
        String pizzaSize = "";
        pizzaSize += pizzaOrder.getSize().substring(0, 1).toUpperCase();
        pizzaSize += pizzaOrder.getSize().substring(1);
        return pizzaSize;
    }
%>
