

$(document).ready(function()
{
	// delete one new 
	$("table.news-data a.rssnew-delete").click(function(){
		// console.info("data = " + $(this).data("class"));
		
		$("table.news-data td." + $(this).data("class")).remove();
		
		return false;
	});
	
	
	// delete all news
	$("table.news-data a.delete-all").click(function(){
		$("table.news-data tbody td").remove();
		
		return false;
	});
	
	
	// get news
	$("table.news-data a.get-news").click(function(){
		console.info("get news ...");
		
		return false;
	});
});
