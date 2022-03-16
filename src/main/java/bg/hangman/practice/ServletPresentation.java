package bg.hangman.practice;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import bg.hangman.config.AppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/game/*")
public class ServletPresentation extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/gamePage.jsp").forward(request, response);
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
    HangmanGameService hangmanGameService = factory.getBean(HangmanGameService.class);
    boolean isNoPathParam = request.getPathInfo() == null || request.getPathInfo().equals("/");
    if (isNoPathParam) {
      hangmanGameService.startNewGame(request, response);
    } else {
      hangmanGameService.makeTry(request, response);
    }
  }



  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getMethod().equals("POST")) {
      doPost(request, response);
    } else if (request.getMethod().equals("GET")) {
      doGet(request, response);
    }
  }


}
