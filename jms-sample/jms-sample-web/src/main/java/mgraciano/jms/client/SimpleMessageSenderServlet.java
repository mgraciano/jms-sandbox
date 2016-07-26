package mgraciano.jms.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mgraciano.jms.request.SimpleMessageSender;

@WebServlet(name = "SimpleMessageSenderServlet", urlPatterns = {"/sendMessage"})
public class SimpleMessageSenderServlet extends HttpServlet {

    @Inject
    private SimpleMessageSender shippingRequest;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Throwable oops = null;
        try {
            shippingRequest.sendMessage();
        } catch (Exception ex) {
            oops = ex;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendMessageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendMessageServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>Time:  " + String.valueOf(new Date()) + "</p>");
            if (oops != null) {
                out.println("<p>Opps:</p>");
                out.println("<xmp>");
                oops.printStackTrace(out);
                out.println("</xmp>");
            } else {
                out.println("<p>Should be OK</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet sendMessage
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet sendMessage
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
