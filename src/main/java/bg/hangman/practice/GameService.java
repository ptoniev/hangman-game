package bg.hangman.practice;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface GameService {
  int getRandomGameId(HttpServletRequest request);

  boolean hasUserWon(HttpServletRequest request);

  void makeTry(HttpServletRequest request, HttpServletResponse response) throws IOException;

  void startNewGame(HttpServletRequest request, HttpServletResponse response) throws IOException;

  String drawPicture(int wrongGuesses);
}
