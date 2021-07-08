document.addEventListener('DOMContentLoaded', function () {
  var calendarEl = document.getElementById('calendario');
  var url = window.location.href.charAt(36);

  var calendar = new FullCalendar.Calendar(calendarEl, {
    locale: 'es',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    editable: true,
    navLinks: true,
    dayMaxEvents: true,
    events: {
      url: 'api/eventos?grupo=' + url,
      failure: function() {
        document.getElementById('error').style.display =  'block';
      }
    },
    loading: function (bool) {
      document.getElementById('cargando').style.display = bool ? 'block' : 'none';
    }
  });

  calendar.render();
});