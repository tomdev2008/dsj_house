
$(function(){
	convertText();
})
 
 function convertText(){
	 var xx=document.getElementById("contentDis").innerText;
	 document.getElementById("content").innerHTML=xx;
 }
