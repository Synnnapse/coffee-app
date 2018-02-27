$('.cross').click(function() {
  this.parentNode.remove();
})

$('.approve').click(function() {
  $(this).parent("li").find(".accepted").show();
})