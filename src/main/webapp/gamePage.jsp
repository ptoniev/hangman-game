<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<style type="text/css">
.menu-header{
  text-align: center;
}
.game-menu{
 top: 10%;
    left: 41%;
    position: fixed;
}

.game-form{
  display: inline-block;
}
</style>

<head>
<meta charset="UTF-8">
<title>Play time</title>
</head>
<body>
	<h1 class="menu-header">Guess like your life depends on it</h1>
	
	<div class="game-menu">
	<p> ${censoredWord} </p>
	
	<p> The word contains ${lettersNumber} letters in total.
	
	<p> You have ${6 - wrongGuessNumber } tries left!</p>
		
	 <pre> ${picture} </pre>
	
	<form class="game-form" method="post" action="/game/${gameId}">
		<table>
			<tr>
				<td>Enter your guessed letter</td>
				<td><input type="text" name="guess" required></td>
				
				
				
			</tr>
		</table>
	</form>
	
	<br>
	<br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >New game</button> </form>
	</div>

</body>
</html>