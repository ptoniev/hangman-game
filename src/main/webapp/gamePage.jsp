<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play time</title>
</head>
<body>
	<h1>Guess like your life depends on it</h1>
	<p> ${censoredWord} </p>
	
	<p> The word contains ${lettersNumber} letters in total.
	
	<p> You have ${6 - wrongGuessNumber } tries left!</p>
		
	 <pre> ${picture} </pre>
	
	<form method="post" action="/game/${gameId}">
		<table>
			<tr>
				<td>Enter your guessed letter</td>
				<td><input type="text" name="guess" required></td>
				
				
				
			</tr>
		</table>
	</form>

</body>
</html>