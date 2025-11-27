package uk.linyi;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;



@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse rep) {
        rep.setContentType("text/html;charset=utf-8");
        rep.getWriter().println("Hello Servlet");
    }

    @Override
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse rep) {
        doGet(req, rep);
    }
}
