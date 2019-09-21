
package com.michzarnowski.michal_zarnowski_a1.servlets;

import com.michzarnowski.michal_zarnowski_a1.model.PizzaOrder;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Place order servlet handles getting pizza details from form filled out by the 
 * user and creating a PizzaOrder object based on entries. Servlet also handles
 * acquiring delivery details if applicable and dispatching JSP page with 
 * order confirmation.
 * @author Michal Zarnowski
 */
public class PlaceOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Get order details from form
        String deliveryMethod = request.getParameter("delMethod");
        boolean pizzaDelivered = false;
        String[] deliveryDetails = new String[3];
        
        //If delivery is selected, get delivery details from form
        if(deliveryMethod.equals("delivery")) {
            pizzaDelivered = true;
            deliveryDetails = validateDeliveryDetails(request, response);
            request.setAttribute("deliveryDetails", deliveryDetails);
        }
        
        //Get pizza size
        String size = request.getParameter("size");
        
        //Get pizza toppings
        String[] toppings = request.getParameterValues("topping");
        
        //Create instance of PizzaOrder
        PizzaOrder order = new PizzaOrder();
        order.setDelivery(pizzaDelivered);
        order.setSize(size);
        
        //If user selected 0 topping, convert null array to empty array
        if(toppings != null)
            order.setToppings(toppings);
        else {
            String[] noToppings = new String[0];
            order.setToppings(noToppings);
        }
        
        //Set request attribute with the pizza order
        request.setAttribute("pizzaOrder", order);
        
        //Forward request to appriopriate JSP page
        String jsp = "/displayOrder.jsp";
        
        //Dispatch order confirmation JSP
        RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
        
    }
    
    /**
     * Method checks if delivery details exist and are not empty. If successful, 
     * details are returned to calling statement, otherwise, deliveryError.html
     * is dispatched.
     * @param request Http Servlet Request object
     * @param response Response object
     * @return Array of Strings holding delivery details
     * @throws ServletException
     * @throws IOException 
     */
    private String[] validateDeliveryDetails(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        //Get delivery details from the form
        String street = request.getParameter("streetAddress").trim();
        String city = request.getParameter("city").trim();
        String postal = request.getParameter("postal").trim();
        
        //Put details inside an array
        String[] deliveryDetails = {street, city, postal};
        
        //Check if any of the details are null or empty strings
        boolean valid = true;
        for(String detail : deliveryDetails) {
            if(detail == null || detail.length() < 1) {
                valid = false;
            }
        }
        
        //If all valid, return array, otherwise, dispatch error page
        if(valid) {
            return deliveryDetails;
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/deliveryError.html");
            rd.forward(request, response);
        }
        
        return new String[0];
        
    }

}
