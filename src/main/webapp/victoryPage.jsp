<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.victory-page{
text-align: center}

.start-game-form{
display: inline-block;
}

</style>
<head>
<meta charset="ISO-8859-1">
<title>Victory</title>
</head>
<body>

<div class="victory-page">
<h1>Congratulations! You have won the game! </h1> <br>

<p>The complete word is: ${gameWord} </p>
<br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
  
  </div>
</body>
</html>