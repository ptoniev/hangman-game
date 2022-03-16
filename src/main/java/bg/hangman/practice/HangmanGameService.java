package bg.hangman.practice;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class HangmanGameService implements GameService {
  HangmanUtils hangmanUtils = new HangmanUtils();

  @Override
  public int getRandomGameId(HttpServletRequest request) {
    Random obj = new Random();
    int randId = obj.nextInt(50);
    HttpSession session = request.getSession();
    session.setAttribute(HangmanUtils.GAME_ID_ATTR, randId);
    return randId;
  }

  @Override
  public boolean hasUserWon(HttpServletRequest request) {
    HttpSession session = request.getSession();
    @SuppressWarnings("unchecked")
    HashSet<Character> guessedLetters =
        (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
    String word = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
    for (int i = 1; i < word.length() - 1; i++) {
      if (!guessedLetters.contains(word.charAt(i))) {
        return false;
      }
    }
    return true;

  }

  @Override
  public void makeTry(HttpServletRequest request, HttpServletResponse response)

      throws IOException {
    HttpSession session = request.getSession();
    Character inputLetter = request.getParameter("guess").charAt(0);
    @SuppressWarnings("unchecked")
    HashSet<Character> guessedLetters =
        (HashSet<Character>) session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR);
    String gameWord = (String) session.getAttribute(HangmanUtils.GAME_WORD_ATTR);
    int wrongGuesses = (int) session.getAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR);
    if (gameWord.indexOf(Character.toUpperCase(inputLetter)) == -1) {
      wrongGuesses++;
      session.setAttribute(HangmanUtils.WRONG_GUESS_NUMBER_ATTR, wrongGuesses);
      session.setAttribute("picture", drawPicture(wrongGuesses));
    }
    if (wrongGuesses >= 6) {
      response.sendRedirect("/defeatPage.jsp");
      return;
    }
    guessedLetters.add(Character.toUpperCase(inputLetter));
    session.setAttribute(HangmanUtils.GUESSED_LETTERS_ATTR, guessedLetters);
    if (hasUserWon(request)) {

      response.sendRedirect("/victoryPage.jsp");
      return;
    }


    hangmanUtils.updateCensoredWord(request);
    response.sendRedirect("/game/" + session.getAttribute("gameId"));
  }

  @Override
  public void startNewGame(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession session = request.getSession();
    session.setAttribute("picture", "");
    getRandomGameId(request);
    hangmanUtils.initWordAndStore(request);
    hangmanUtils.countLettersInGameWord(request);
    response.sendRedirect("/game/" + getRandomGameId(request));

  }

  @Override
  public String drawPicture(int wrongGuesses) {
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
