<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Victory</title>
</head>
<body>
<h1>Congratulations! You have won the game! </h1> <br>

<p>The complete word is: ${gameWord} </p>
<br>
<form method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
</body>
</html>