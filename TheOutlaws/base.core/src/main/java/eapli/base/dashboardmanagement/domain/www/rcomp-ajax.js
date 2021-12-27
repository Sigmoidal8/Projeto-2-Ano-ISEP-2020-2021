
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
        setTimeout(refreshPersonalInfo, 500);
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

function refreshTasksByCriticality() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("tasksOrderByCriticality");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshTasksByCriticality, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByCriticality, 500);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByCriticality, 5000);
    };

    request.open("GET", "/tasksOrderByCriticality", true);
    request.timeout = 5000;
    request.send();
}

function refreshTasksByPriority() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("tasksOrderByPriority");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshTasksByPriority, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByPriority, 500);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByPriority, 5000);
    };

    request.open("GET", "/tasksOrderByPriority", true);
    request.timeout = 5000;
    request.send();
}

function refreshTasksByRequestDate() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("tasksOrderByRequestDate");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshTasksByRequestDate, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByRequestDate, 500);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTasksByRequestDate, 5000);
    };

    request.open("GET", "/tasksOrderByRequestDate", true);
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


