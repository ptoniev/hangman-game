package bg.hangman.practice;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class GameService extends WordContainer {

  public int getRandomGameId(HttpServletRequest request) {
    Random obj = new Random();
    int randId = obj.nextInt(50);
    HttpSession session = request.getSession();
    session.setAttribute(GAME_ID_ATTR, randId);
    return randId;
  }

  private boolean hasUserWon(HttpServletRequest request) {
    HttpSession session = request.getSession();
    HashSet<Character> guessedLetters =
        (HashSet<Character>) session.getAttribute(GUESSED_LETTERS_ATTR);
    String word = (String) session.getAttribute(GAME_WORD_ATTR);
    for (int i = 1; i < word.length() - 1; i++) {
      if (!guessedLetters.contains(word.charAt(i))) {
        return false;
      }
    }
    return true;

  }

  public void makeTry(HttpServletRequest request, HttpServletResponse response)

      throws IOException {
    HttpSession session = request.getSession();
    Character inputLetter = request.getParameter("guess").charAt(0);
    HashSet<Character> guessedLetters =
        (HashSet<Character>) session.getAttribute(GUESSED_LETTERS_ATTR);
    String gameWord = (String) session.getAttribute(GAME_WORD_ATTR);
    int wrongGuesses = (int) session.getAttribute(WRONG_GUESS_NUMBER_ATTR);
    if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
      wrongGuesses++;
      session.setAttribute(WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
      session.setAttribute("picture", drawPicture(wrongGuesses));
    }
    if (wrongGuesses >= 6) {
      response.sendRedirect("/defeatPage.jsp");
      return;
    }
    guessedLetters.add(Character.toUpperCase(inputLetter));
    session.setAttribute(GUESSED_LETTERS_ATTR, guessedLetters);
    if (hasUserWon(request)) {

      response.sendRedirect("/victoryPage.jsp");
      return;
    }


    updateCensoredWord(request);
    response.sendRedirect("/game/" + session.getAttribute("gameId"));
  }

  public void startNewGame(HttpServletRequest request, HttpServletResponse response,
      ServletContext servletContext) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("picture", " ");
    getRandomGameId(request);
    initWordAndStore(request);
    countLettersInGameWord(request);
    response.sendRedirect("/game/" + getRandomGameId(request));

  }

  private String drawPicture(int wrongGuesses) {
    switch (wrongGuesses) {
      case 1:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|<br>" + "|<br>" + "|";
      case 2:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|     |<br>" + "|<br>" + "|";
      case 3:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|<br>" + "|<br>" + "|";
      case 4:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|<br>" + "|";
      case 5:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|    /<br>" + "|";
      case 6:
        return "_____<br>" + "|     |<br>" + "|     O<br>" + "|    /|\\<br>" + "|    / \\<br>"
            + "|";
      default:
        return "  ";
    }

  }
}
