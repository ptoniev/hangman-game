package bg.hangman.practice;

import java.util.HashSet;
import java.util.Random;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class HangmanUtils {
  protected static final String GAME_WORD_ATTR = "gameWord";
  protected static final String WRONG_GUESS_NUMBER_ATTR = "wrongGuessNumber";
  protected static final String GUESSED_LETTERS_ATTR = "guessedLetters";
  protected static final String GAME_ID_ATTR = "gameId";

  protected String getRandomWord() {
    String[] words = {"Bmw", "Audi", "Mercedes", "Bentley", "Ferrari", "Opel", "Maseratti", "Fiat",
        "Hyundai", "Volkswagen", "Tesla"};
    Random obj = new Random();
    int randNum = obj.nextInt(11);
    String word = (words[randNum]).toUpperCase();
    return word;
  }

  protected void initWordAndStore(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String gameWord = getRandomWord();
    session.setAttribute(GAME_WORD_ATTR, gameWord);
    session.setAttribute(WRONG_GUESS_NUMBER_ATTR, 0);
    session.setAttribute(GUESSED_LETTERS_ATTR, new HashSet<Character>());
    updateCensoredWord(request);

  }

  protected void updateCensoredWord(HttpServletRequest request) {
    HttpSession session = request.getSession();
    @SuppressWarnings("unchecked")
    HashSet<Character> guessedLetters =
        (HashSet<Character>) session.getAttribute(GUESSED_LETTERS_ATTR);
    String word = (String) session.getAttribute(GAME_WORD_ATTR);
    String wordToReturn = word;
    for (int i = 1; i < word.length() - 1; i++) {
      if (!guessedLetters.contains(word.charAt(i))) {
        wordToReturn = wordToReturn.replace(word.charAt(i), '_');
      }
    }
    session.setAttribute("censoredWord", wordToReturn);
  }

  protected void countLettersInGameWord(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String gameWord = (String) session.getAttribute(GAME_WORD_ATTR);
    int lettersNumber = gameWord.length();
    session.setAttribute("lettersNumber", lettersNumber);
  }
}
