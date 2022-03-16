<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.home-page{
text-align: center}

.start-game-form{
display: inline-block;
}

</style>
<head>
<meta charset="UTF-8">
<title>Hangman Game</title>
</head>
<body>
<div class="home-page">
<h1>Welcome to the Hangman Game!</h1>
  	
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  	</div>
</body>
</html>