<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loser</title>
</head>
<body>
<p> You have lost the game and virtual death is upon you!</p>
	 <pre> ${picture} </pre>
	 <br>
<form method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
</body>
</html>