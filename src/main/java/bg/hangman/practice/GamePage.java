package bg.hangman.practice;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/game")
public class GamePage extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String GAME_WORD_ATTR = "gameWord";
  private static final String WRONG_GUESS_NUMBER_ATTR = "wrongGuessNumber";
  private static final String GUESSED_LETTERS_ATTR = "guessedLetters";

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String gameWord = generateWord();
    getServletContext().setAttribute(GAME_WORD_ATTR, gameWord);
    getServletContext().setAttribute(WRONG_GUESS_NUMBER_ATTR, 0);
    getServletContext().setAttribute(GUESSED_LETTERS_ATTR, new HashSet<Character>());
    updateCensoredWord();
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gamePage.jsp");
    dispatcher.forward(request, response);

  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("test");
    Character inputLetter = request.getParameter("guess").charAt(0);
    HashSet<Character> guessedLetters =
        (HashSet<Character>) getServletContext().getAttribute(GUESSED_LETTERS_ATTR);
    String gameWord = (String) getServletContext().getAttribute(GAME_WORD_ATTR);
    int wrongGuesses = (int) getServletContext().getAttribute(WRONG_GUESS_NUMBER_ATTR);
    if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
      wrongGuesses++;
      getServletContext().setAttribute(WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
    }
    if (wrongGuesses >= 5) {
      response.sendRedirect("/defeatPage.jsp");
      return;
    }
    guessedLetters.add(Character.toUpperCase(inputLetter));
    getServletContext().setAttribute(GUESSED_LETTERS_ATTR, guessedLetters);
    if (hasUserWon()) {
      System.out.println("Teest1");
      response.sendRedirect("/victoryPage.jsp");
      return;
    }


    updateCensoredWord();
    response.sendRedirect("/gamePage.jsp");
  }


  private String generateWord() {
    String[] words = {"Bmw", "Audi", "Mercedes", "Bentley", "Ferrari"};
    Random obj = new Random();
    int randNum = obj.nextInt(5);
    String word = (words[randNum]).toUpperCase();
    // String censoredWord = word.replaceAll("[A-Z]", "_ ");
    return word;
  }

  public void updateCensoredWord() {
    HashSet<Character> guessedLetters =
        (HashSet<Character>) getServletContext().getAttribute(GUESSED_LETTERS_ATTR);
    String word = (String) getServletContext().getAttribute(GAME_WORD_ATTR);
    String wordToReturn = word;
    for (int i = 0; i < word.length(); i++) {
      if (!guessedLetters.contains(word.charAt(i))) {
        wordToReturn = wordToReturn.replace(word.charAt(i), '_');
      }
    }
    getServletContext().setAttribute("censoredWord", wordToReturn);
    System.out.println(wordToReturn);
  }

  private boolean hasUserWon() {
    HashSet<Character> guessedLetters =
        (HashSet<Character>) getServletContext().getAttribute(GUESSED_LETTERS_ATTR);
    String word = (String) getServletContext().getAttribute(GAME_WORD_ATTR);
    for (char c : word.toCharArray()) {
      System.out.println(c);
      if (!guessedLetters.contains(c) && c != 0) {
        return false;
      }
    }
    return true;

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
