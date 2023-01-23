$(document).ready(function(){
$("#sel").change(function(){
$('#form1').submit();
});
  
$("#sel2").change(function(){
$('#form1').submit();
});


$(".l")
.mouseover(function() {
	let id = $(this).data('id')
 console.log(id);
	$.ajax({
      	url: "/pop",  // リクエストを送信するURLを指定（action属性のurlを抽出）
      	type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
      	data: {
        id: id  // 送信データ
        },
      	datatype : "json"
    })
    
    	.done(function(data) {
			var data_stringify = JSON.stringify(data);
      		var data_json = JSON.parse(data_stringify);
      		var p = "うまく機能せず";
      		console.log(p);
      		console.log(id);
        	$.each(data_json, function(index, val) {
				var i = val.name;
				console.log(i);
    			console.log(index);
    			$("#mention-list2").append("<tr><td><img src='/img/"+ val.id +".jpg'height='50px'width='68px'></td><td>"+ val.name +"</td><td>ジャンル : "+ val.genre +"</td><td>  価格   : "+ val.value +"円(税込み)</td>  <td>made for : "+ val.maker +"</td></tr>");
    		
  				//$("#name").append("<div class='card mb-3' style='max-width: 1080px;'><div class='row g-0'><div class='col-md-4'><img src='/img/"+ val.id+".jpg'  height='150px'width='200px'></div><div class='col-md-8'><div class='samplej' id='scr' ><h3 class='card-title'><input type='checkbox' name='checkbox' value='"+ val.id +"'><td>"+ val.name +"</td></h3><h6 class='card-title'>ジャンル : <td>" + val.genre +"</td></h6><h6 class='card-title'>  価格   : <td>" + val.value +"</td>円(税込み)</h6><h6 class='card-title'> 説明 : " + val.exp + "</h6><p class='card-text'><small class='text-muted'>made for : " + val.maker+ "</small></p></div> </div></div></div>");
  		    });
  		    $(".mention-list2").Toggle();//fadeToggle 何故か使用失敗
		})
		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
      		alert("error!");  // 通信に失敗した場合の処理
      		console.log("XMLHttpRequest : " + XMLHttpRequest.status);
    		console.log("textStatus     : " + textStatus);
    		console.log("errorThrown    : " + errorThrown.message);
    	});
})
.mouseout(function() {
   $('#mention-list2').find('tr').remove();
   $(".mention-list2").Toggle();
});
});