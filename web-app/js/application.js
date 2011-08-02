$(document).ready(function () {

	$('#jqueryversion').html($.fn.jquery);
	$('#modernizrversion').html(Modernizr._version);

	// make the min-height of column1 equal to the height of column2
	
	$(window).resize(function () {
		$('#column1').css('min-height', $('#column2').height());
	});

	$(window).resize();
	
	$(".warndelete").click(function() {
		return confirm('Are you sure you want to delete this item?');
	});
	
});


