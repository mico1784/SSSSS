      var ws;
      var userName = $("#username").val() || '게스트';

      $(document).ready(function() {
          wsOpen();
      });

      function wsOpen(){
        ws = new WebSocket("ws://" + location.host + "/chatting");
        wsEvt();
      }

      function wsEvt(){
        ws.onopen = function(data){

        }

        ws.onmessage = function(data){
            var msg = data.data;
            if(msg != null && msg.trim() != ''){
                $("#chating").append("<p>" + msg + "</p>");
            }
        }

        document.addEventListener("keypress", function(e){
            if(e.keyCode == 13){
                send();
            }
        });
      }

	function send() {
		var msg = $("#chatting").val();
		ws.send(userName + " : " + msg);
		$('#chatting').val("");
	}