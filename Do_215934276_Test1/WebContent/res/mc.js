/**
 * 
 */

function validate() {
	var ok = true;
	var a = document.getElementById("principal").value;
	if (isNaN(a) || a <= 0) {
		alert("Invalid principal!");
		document.getElementById("principal-error").style.display = "inline";
		document.getElementById("principal-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("principal-error").style.display = "none";
	}

	var i = document.forms["myForm"]["interest"].value;
	if (isNaN(i) || i <= 0 || i >= 100) {
		alert("Invalid interest! Must be in (0,100).");
		document.getElementById("interest-error").style.display = "inline";
		document.getElementById("interest-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("interest-error").style.display = "none";
	}

	var p = document.forms["myForm"]["period"].value;
	if (isNaN(p) || p <= 0) {
		alert("Invalid period!");
		document.getElementById("period-error").style.display = "inline";
		document.getElementById("period-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("period-error").style.display = "none";
	}
	var g = document.forms["myForm"]["gracePeriod"].value;
	if (isNaN(g) || g < 6 || g > 12) {
		alert("Grace Period should be between 6 and 12");
		document.getElementById("gPeriod-error").style.display = "inline";
		document.getElementById("gPeriod-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("gPeriod").style.display = "none";

	}

	return ok;

}

function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = '';
	var checkBox = document.getElementById("grace");

	/* add your code here to grab all parameters from form */
	data += "principal=" + document.getElementById("principal").value + "&";
	data += "interest=" + document.getElementById("interest").value + "&";
	data += "period=" + document.getElementById("period").value + "&";
	if (checkBox.checked == true) {
		data += "grace=" + document.getElementById("grace").value + "&";
	}
	data += "calculate=true";
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();

}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
	}
}
