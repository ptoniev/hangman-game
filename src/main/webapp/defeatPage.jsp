<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style type="text/css">
.defeat-header{
  text-align: center;
}
.defeat-page{
   top: 15%;
    left: 46%;
    position: fixed;	
}

.start-game-form{
display: inline-block;
}

</style>
<head>
<meta charset="UTF-8">
<title>Loser</title>
</head>
<body>
<h1 class="defeat-header"> You have lost the game and virtual death is upon you!</h1>
<div class="defeat-page">
	 <pre> ${picture} </pre>
	 <br>
<form class="start-game-form" method="post" action="/game">
  <button type="submit" >Start a new game</button> </form>
  
  
  </div>
</body>
</html>