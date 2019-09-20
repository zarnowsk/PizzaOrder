
package com.michzarnowski.michal_zarnowski_a1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michal Zarnowski
 */
public class StartOrder extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Collect user data from request
        String name = getName(request, response);
        String tel = request.getParameter("tel");
        
        //Set name and phone number as session attributes for access in JSP
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("tel", tel);
        
        //Prepare output strings containing user name + tel
        String nameFormat = "<h1>Hi %s</h1>";
        String telFormat = "<h2>%s</h2>";
        
        //Prepare order form HTML
        String orderForm = "<form action='PlaceOrder.do' method='POST'><fieldset><legend>New Pizza Order</legend>";
        String delMethod = "Pick up or delivery <select name='delMethod'>\n" + 
                "<option value='pickUp'>Pick-up</option>\n" + 
                "<option value='delivery'>Delivery</option>\n" + 
                "</select><br><br>";
        String size = 
                "<input type='radio' name='size' value='small' checked> Small ($5)\n" + 
                "<input type='radio' name='size' value='medium'> Medium ($7)\n" +
                "<input type='radio' name='size' value='large'> Large ($9)<br><br>\n";
        String toppings = 
                "<input type='checkbox' name='topping' value='pepperoni'> Pepperoni<br>\n" +
                "<input type='checkbox' name='topping' value='sausage'> Sausage<br>\n" +
                "<input type='checkbox' name='topping' value='spinach'> Baby Spinach<br>\n" +
                "<input type='checkbox' name='topping' value='pepper'> Pepper<br><br>\n";
        String submit = "<div class='buttonHolder'><input type='submit' value='Place My Order'></div>";
        String formEnd = "</fieldset></form>";

        //Build HTML with print writer (egh...)
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Place your order</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='css/main.css' />");
            out.println("<link href='https://fonts.googleapis.com/css?family=Lobster&display=swap' rel='stylesheet'>" );
            out.println("</head>");
            out.println("<body>");
            out.println(String.format(nameFormat, name));
            out.println(String.format(telFormat, tel));
            out.println(orderForm);
            out.println(delMethod);
            out.println(size);
            out.println(toppings);
            out.println(submit);
            out.println(formEnd);
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     * Method validates the name entered by the user in form inside index.html.
     * If name exists and is not empty, it will be returned to calling statement, 
     * otherwise method will dispatch index.html
     * @param request Http Servlet Request object
     * @param response Http Servlet Response object
     * @return name entered by the user
     * @throws ServletException
     * @throws IOException 
     */
    private String getName(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{

        //Get the name entered by the user in the form and clear of empty spaces
        String name = request.getParameter("name").trim();
        
        //If name exists and is not empty return it, otherwise dispatch index.html
        if(name != null && name.length() > 0) {
            return name;
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            rd.forward(request, response);
        }
        
        return "";
    }

}
