package bg.hangman.practice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class HangmanGameServiceTest {
  private HashSet<Character> guessedLetters;
  private String word;
  private HangmanGameService service;

  @BeforeEach
  void init() {
    guessedLetters = new HashSet<Character>();
    word = "desk";
    guessedLetters.add('e');
    guessedLetters.add('s');
    service = new HangmanGameService();
  }

  @Test
  void testNewGameCreation() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    Mockito.when(request.getSession()).thenReturn(session);
    Mockito.when(session.getAttribute(HangmanUtils.GUESSED_LETTERS_ATTR))
        .thenReturn(guessedLetters);
    Mockito.when(session.getAttribute(HangmanUtils.GAME_WORD_ATTR)).thenReturn(word);

    assertThat(service.hasUserWon(request)).isTrue();
  }


}
