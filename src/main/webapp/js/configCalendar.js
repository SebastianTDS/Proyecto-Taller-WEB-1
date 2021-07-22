document.addEventListener('DOMContentLoaded', function () {
  var calendarEl = document.getElementById('calendario');
  var url = window.location.href.split("/")[5];

  var modalInitJS = new BSN.Modal('#myModal', {
    backdrop: 'static',
    keyboard: true
  });

  var calendar = new FullCalendar.Calendar(calendarEl, {
    locale: 'es',
    customButtons: {
      nuevoEvento: {
        text: 'Nuevo Evento',
        click: function () {
          modalInitJS.show();
        }
      }
    },
    headerToolbar: {
      left: 'prev,next today nuevoEvento',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    editable: true,
    navLinks: true,
    dayMaxEvents: true,
    events: {
      url: 'api/eventos?grupo=' + url,
      failure: function () {
        document.getElementById('error').style.display = 'block';
      }
    },
    loading: function (bool) {
      document.getElementById('cargando').style.display = bool ? 'block' : 'none';
    }
  });

  document.getElementById("cerrarModal").addEventListener("click", function () { modalInitJS.hide(); });

  document.getElementById("nuevoEvento").addEventListener("submit", function(event){
    event.preventDefault();

    var nuevoEvento = 
    "start=" + document.getElementById("start").value +
    "&end=" + document.getElementById("end").value +
    "&title=" + document.getElementById("title").value;
    

    AJAXRequest('api/eventos/' + url + '/nuevo', 'POST', nuevoEvento);
    modalInitJS.hide();
  });

  document.getElementById('myModal').addEventListener('hide.bs.modal', function(event){
    document.getElementById("start").value = null;
    document.getElementById("end").value = null;
    document.getElementById("title").value = null;
  }, false);

  calendar.render();

  function AJAXRequest(url, metodo, evento = null){
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
      	document.getElementById("error").style.display = "none";
        calendar.refetchEvents();
      }else if(this.readyState == 4){
      	document.getElementById("error").style.display = "block";
      	document.getElementById("errorMsg").innerHTML = this.responseText;
      }
    };
    xhttp.open(metodo, url);
    xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhttp.send(evento);
  }
});