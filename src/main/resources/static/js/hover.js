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
 console.log("マウスオーバー"+ id);
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
        	$.each(data_json, function(index, val) {
    			$("#mention-list2").append("<tr><td><img src='/img/"+ val.id +".jpg'height='50px'width='68px'></td><td>"+ val.name +"</td><td>ジャンル : "+ val.genre +"</td><td>  価格   : "+ val.value +"円(税込み)</td>  <td>made for : "+ val.maker +"</td></tr>");
  		    });
  		    $("#mention-list2").stop(true,true).fadeToggle();
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
});

$(function(){
  $("#toggle").on("click", function() {
    $(".toggle-list").slideToggle();
  });
});
$(function(){
  $("#wrote").on("click", function() {
    $(".review-list").Toggle();
  });
});

