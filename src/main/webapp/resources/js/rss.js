

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
	
	// delete feed
	$("table.feeds-data a.rssfeed-delete").click(function()
	{
		var tdSelector = "table.feeds-data td." + $(this).data("class");
		$(tdSelector).css("background-color", "#FF5500");
		
		// example: delFeedUrl = http://localhost:8080/RssAggregator/feed/delete/7
        var delFeedUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href"); 

		$.ajax({
		    url: delFeedUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				if(data.code != 1){
				    alert(data.message);
				}
				else {
					$(tdSelector).remove();
				}
			}
		});
		
		return false;
	});
});
