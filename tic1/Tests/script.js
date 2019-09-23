


document.addEventListener("DOMContentLoaded",
	function (event){

console.log(document.getElementById("title"));

		function sayHello(event){

console.log(event);


console.log(
 document.getElementById("in1").value
	);


var name=document.getElementById("in1").value;
var message;

if(name=="juan" || name=="Juan"){
	message="<h2>a "+ name+" le gusta minecraft</h2>";

	 document.querySelector("#title").textContent=" CREPEEEEER";
}

if(name=="daniel" || name=="Daniel"){
	message="soy "+ name+" el seductor y he venido a cantarte mi amor";
}

if(name =="que hay de su vida"){
	message="y lo mismo que de bajada ";
}

//document.getElementById("contenido").textContent=message;

document.getElementById("contenido").innerHTML=message;
}

document.querySelector("button").onclick=sayHello;

document.querySelector("body").addEventListener("mousemove",
	function (event) {
		if(event.shiftKey===true){
		console.log(event.clientX);
		console.log(event.clientY);
	}
	});




	})



