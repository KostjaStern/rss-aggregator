

$(document).ready(function()
{
	function addAjaxLoader()
	{
		// http://localhost:8080/RssAggregator/resources/img/ajax-loader.gif
		var imgLink = document.location.protocol + "//" + document.location.host + "/RssAggregator/resources/img/ajax-loader.gif"; 
		var html = "<div class='ajax-loader'>" +
		           "<img class='loader-img' src='" + imgLink + "'/>" +
		           "</div>";
		
		$("body").append(html);
	}
	
	function removeAjaxLoader()
	{
		$("div.ajax-loader").remove();
	}
	
	// delete rss news
	$(document).on("click", "table.news-data a.rssnew-delete", function() {
		var tdSelector = "table.news-data td." + $(this).data("class");
		$(tdSelector).css("background-color", "#FF5500");
		addAjaxLoader();
		
		
		var delNewsUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href");
		console.info("delNewsUrl = " + delNewsUrl);
		
		$.ajax({
		    url: delNewsUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				removeAjaxLoader();
				if(data.code == 1){
					$(tdSelector).remove();
				} 
				else {
					$(tdSelector).css("background-color", "#FFFFFF");
					alert(data.message);
				}
			}
		});
		
		return false;
	});
	
	
	
	// delete all news
	$("table.news-data a.delete-all").click(function(){
		var delAllNewsUrl = document.location.protocol + "//" + document.location.host + $(this).attr("href");
		// console.info("delAllNewsUrl = " + delAllNewsUrl);
		addAjaxLoader();
		
		$.ajax({
		    url: delAllNewsUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				removeAjaxLoader();
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
		// console.info("getNewsUrl = " + getNewsUrl);
		addAjaxLoader();
		
		$.ajax({
		    url: getNewsUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				removeAjaxLoader();
				
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
					}
					$("table.news-data tbody").html(html);
				}

				$("div.message").html(data.message);
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
        addAjaxLoader();
        
		$.ajax({
		    url: delFeedUrl,
			type: "POST",
			dataType: "json",
			success	: function(data)
			{
				removeAjaxLoader();
				
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
