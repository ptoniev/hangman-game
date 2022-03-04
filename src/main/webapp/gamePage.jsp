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
	<h1>Let's play the hang man game</h1>
	<p> ${censoredWord} </p>
	<p> You have ${5 - wrongGuessNumber } tries left!</p>
	
	<form method="post" action="/game">
		<table>
			<tr>
				<td>Enter your guessed letter</td>
				<td><input type="text" name="guess" required></td>
				
				
				
			</tr>
		</table>
	</form>

</body>
</html>