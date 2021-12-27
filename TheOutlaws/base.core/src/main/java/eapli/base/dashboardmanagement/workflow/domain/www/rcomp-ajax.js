
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshVotes() {
	var request = new XMLHttpRequest();
        var vBoard=document.getElementById("votes");

        request.onload = function() {
            vBoard.innerHTML = this.responseText;
            vBoard.style.color="black";
            setTimeout(refreshVotes, 2000);
            };

        request.ontimeout = function() {
            vBoard.innerHTML = "Server timeout, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshVotes, 100);
        };

        request.onerror = function() {
            vBoard.innerHTML = "No server reply, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshVotes, 5000);
        };

  	request.open("GET", "/votes", true);
	request.timeout = 5000;
  	request.send();
	}

function refreshPersonalInfo() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("personalInfo");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 5000);
    };

    request.open("GET", "/personalInfo", true);
    request.timeout = 5000;
    request.send();
}


function refreshRequests() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("requests");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshRequests, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshRequests, 500);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshRequests, 5000);
    };

    request.open("GET", "/requests", true);
    request.timeout = 5000;
    request.send();
}

function voteFor(option) {
	var request = new XMLHttpRequest();
  	request.open("PUT", "/votes/" + option , true);
  	request.send();
        var vBoard=document.getElementById("votes");
        vBoard.innerHTML = vBoard.innerHTML + "<p>Casting your vote ... Please wait.";

	}


