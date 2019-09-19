
package com.michzarnowski.michal_zarnowski_a1.servlets;

import com.michzarnowski.michal_zarnowski_a1.model.PizzaOrder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
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
        if(deliveryMethod.equals("delivery"))
            pizzaDelivered = true;
        
        String size = request.getParameter("size");
        
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
        
        //Forward request to JSP page
        String jsp = "/displayOrder.jsp";
        RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PlaceOrder</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>" + order.toString() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

}
