package com.arobs.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = "/test",
        initParams = {
                @WebInitParam(name = "param1", value = "hi"),
                @WebInitParam(name = "param2", value = "hiHi")
        }
)

public class ServletTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String param1 = getInitParameter("param1");
        String param2 = getInitParameter("param2");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        try {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Update Page</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<center>");
            writer.println("<h1>SERVLET EXAMPLE</h1>");
            writer.println("</center>");

            writer.println(" param1 = " + param1);
            writer.println("</br>");
            writer.println("param2 = " + param2);
            writer.println("</body>");
            writer.println("</html>");
        } finally {
            writer.close();
        }
    }
}
