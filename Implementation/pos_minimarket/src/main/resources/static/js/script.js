$(document).ready(function(){
  $('.menu').click(function(){
    $('ul').toggleClass('active');
  })
})
function closeNav() {
    document.getElementById("dr-down").style.display="none";
}
function openNav() {
    document.getElementById("dr-down").style.display="block";
}

function hideColoum() {
    document.getElementById("hide").style.display="none";
}
