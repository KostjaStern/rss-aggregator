

$(document).ready(function()
{
	// delete rss news
	$(document).on("click", "table.news-data a.rssnew-delete", function() {
		var tdSelector = "table.news-data td." + $(this).data("class");
		$(tdSelector).css("background-color", "#FF5500");
		
		var delNewsUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href");
		console.info("delNewsUrl = " + delNewsUrl);
		
		// $("table.news-data td." + $(this).data("class")).remove();
		
		return false;
	});
	
	
	
	// delete all news
	$("table.news-data a.delete-all").click(function(){
		var delAllNewsUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href");
		console.info("delAllNewsUrl = " + delAllNewsUrl);
		
		$.ajax({
		    url: delAllNewsUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				if(data.code == 1){
					$("table.news-data tbody tr").remove();
				}
				$("div.message").html(data.message);
			}
		});
		
		return false;
	});
	
	
	// get news
	$("table.news-data a.get-news").click(function(){
		var getNewsUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href");
		console.info("getNewsUrl = " + getNewsUrl);
		
		$.ajax({
		    url: getNewsUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				console.info("data.code = " + data.code);
				console.info("data.message = " + data.message);
				
				if(data.code == 1)
				{
					var html = "";
					
					for(var k in data.news)
					{
						html += "<tr><td class='rssnew row-" + data.news[k].newId + "'>" +  
							    "<a class='rssnew-link' href='" + data.news[k].newLink + "'>" + data.news[k].newTitle + "</a>" +
                                "</td><td class='rssfeed-name row-" + data.news[k].newId + "'>" + data.news[k].newFeedName + "</td>" +
						        "<td class='rssnew-delete row-" + data.news[k].newId + "'>" +
						        "<a class='rssnew-delete button' href='/RssAggregator/news/delete/" + data.news[k].newId + 
						        "' data-class='row-" + data.news[k].newId + "'>Delete</a></td></tr>";
					   /*	
						console.info("data.news[" + k + "].newId = " + data.news[k].newId);
						console.info("data.news[" + k + "].newTitle = " + data.news[k].newTitle);
						console.info("data.news[" + k + "].newLink = " + data.news[k].newLink);
						console.info("data.news[" + k + "].newFeedName = " + data.news[k].newFeedName);
					   */
					}
					$("table.news-data tbody").html(html);
				}
				else
				{
					$("div.message").html(data.message);
				}
			}
		});
		
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
				    $(tdSelector).css("background-color", "#FFFFFF");
				}
				else {
					$(tdSelector).remove();
				}
			}
		});
		
		return false;
	});
});
