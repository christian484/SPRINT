package pattern;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import initiale.*;

public class FrontController extends HttpServlet {
    List<String> controllerList;
    HashMap<String, Mapping> urlMethod;
    Utile utl;

    @Override 
    public void init() throws ServletException {
        controllerList = new ArrayList<>();
        urlMethod = new HashMap<>();
        utl = new Utile();
        utl.initializeControllers(this, this.controllerList, urlMethod);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FrontController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontController</h1>");
            out.println("<p>" + request.getRequestURL() + "</p>");
            if (utl.ifMethod(request, this.urlMethod) != null) {
                Mapping mapping = utl.ifMethod(request, this.urlMethod);
                out.println("<p> Classe : " + mapping.getKey() + "</p>");
                out.println("<p> Mehtode: " + mapping.getValue() + "</p>");
                out.println("<p> Value returned : " + this.utl.callMethod(mapping) + "</p>");
                Object o = this.utl.callMethod(mapping);

                if(o instanceof String) {
                    out.println(o.toString());
                } else if (o instanceof ModelView) {
                    ModelView mv = (ModelView) o;
                    RequestDispatcher disp = request.getRequestDispatcher(mv.getName());

                    for(Map.Entry<String, Object> entry : mv.getList().entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }

                    disp.forward(request, response);
                }
            } else {
                out.println("<p> Error 404 : Not found </p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
