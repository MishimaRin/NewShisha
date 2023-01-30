$(window).on('scroll', function(e){
	 e.preventDefault();  // デフォルトのイベント(ページの遷移やデータ送信など)を無効にする
    var docHeight = $(document).innerHeight(), 
        windowHeight = $(window).innerHeight(), 
        pageBottom = docHeight - windowHeight;
    
    // ajaxコンテンツ追加処理
    if(pageBottom <= $(window).scrollTop()) {
    	$.ajax({
      	url: "/mensk",  // リクエストを送信するURLを指定（action属性のurlを抽出）
      	type: "GET",  // HTTPメソッドを指定（デフォルトはGET）
      	datatype : "json"
    	})
    	.done(function(data) {
			var data_stringify = JSON.stringify(data);
      		var data_json = JSON.parse(data_stringify);
      		
      		
        	$.each(data_json, function(index, val) {
				
				if(!data) {
					alert("もうない");
      			return false;
    			}
    			
    			console.log(index);
    		
  				$("#name").append("<div class='card mb-3' style='max-width: 1080px;'><div class='row g-0'><div class='col-md-4'><img src='/img/"+ val.id+".jpg'  height='150px'width='200px'></div><div class='col-md-8'><div class='samplej' id='scr' ><h3 class='card-title'><input type='checkbox' name='checkbox' value='"+ val.id +"'><td>"+ val.name +"</td></h3><h6 class='card-title'>ジャンル : <td>" + val.genre +"</td></h6><h6 class='card-title'>  価格   : <td>" + val.value +"</td>円(税込み)</h6><h6 class='card-title'> 説明 : " + val.exp + "</h6><p class='card-text'><small class='text-muted'>made for : " + val.maker+ "</small></p></div> </div></div></div>");
  				
    			
    			
   				
			});
			
    			
    		
    	})
    	.fail(function(XMLHttpRequest, textStatus, errorThrown) {
      		alert("error!");  // 通信に失敗した場合の処理
      		console.log("XMLHttpRequest : " + XMLHttpRequest.status);
    		console.log("textStatus     : " + textStatus);
    		console.log("errorThrown    : " + errorThrown.message);
    	})
     }
});
$(document).ready(function(){
$("#sel").change(function(){
$('#form1').submit();
});
  
$("#sel2").change(function(){
$('#form1').submit();
});
});


/*$(function(){
$("#bc").on("click", function(){
	var k = "koo";
	console.log(k);
	let id = $(this).data('id')
	console.log(id);
	$.ajax({
      	url: "/makeMixA",  // リクエストを送信するURLを指定（action属性のurlを抽出）
      	type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
      	data: {
        id: id // 送信データ
        },
      	datatype : "json"
    })
    
    	.done(function(data) {
	var g = "goo";
	console.log(g);
			var data_stringify = JSON.stringify(data);
      		var data_json = JSON.parse(data_stringify);
        	$.each(data_json, function(index, val) {
    			//$("#mention-list2").append("<tr><td><img src='/img/"+ val.id +".jpg'height='50px'width='68px'></td><td>"+ val.name +"</td><td>ジャンル : "+ val.genre +"</td><td>  価格   : "+ val.value +"円(税込み)</td>  <td>made for : "+ val.maker +"</td></tr>");
  		    });
  		    //$("#mention-list2").stop(true,true).fadeToggle();
		})
		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
      		alert("error!");  // 通信に失敗した場合の処理
      		console.log("XMLHttpRequest : " + XMLHttpRequest.status);
    		console.log("textStatus     : " + textStatus);
    		console.log("errorThrown    : " + errorThrown.message);
    	});
})
.mouseout(function() {
	let id = $(this).data('id')
 console.log("マウスアウト"+ id);
   $('#mention-list2').find('tr').remove();
   $("#mention-list2").fadeToggle();
});
});*/


$(function(){
    $('#for').on('click', function() {
        var checks=[];
        $("[name='checkbox']:checked").each(function(){
            checks.push(this.value);
            console.log("選ばれたもの" + this.value);
        });
        console.log("json" + JSON.stringify(checks));
        	$.ajax({    
            	url: "/checks",
            	type: "POST",
            	data:  JSON.stringify(checks),
                contentType: 'application/json',
            	datatype : "json"
            })
            	.done(function(data) {
	console.log("送ったのはString");
	
					//var data_stringify = JSON.stringify(data);
      				//var data_json = JSON.parse(data_stringify);
       				///$.each(data_json, function(index, val) {
    					//$("#mention-list2").append("<tr><td><img src='/img/"+ val.id +".jpg'height='50px'width='68px'></td><td>"+ val.name +"</td><td>ジャンル : "+ val.genre +"</td><td>  価格   : "+ val.value +"円(税込み)</td>  <td>made for : "+ val.maker +"</td></tr>");
    					console.log("json取得"+data);
  	    			//});
	    			
				})
				.fail(function(XMLHttpRequest, textStatus, errorThrown) {
      				alert("error!");  // 通信に失敗した場合の処理
   					console.log("XMLHttpRequest : " + XMLHttpRequest.status);
   					console.log("textStatus     : " + textStatus);
   					console.log("errorThrown    : " + errorThrown.message);
   				});
		})
})