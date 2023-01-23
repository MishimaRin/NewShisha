$(window).on('scroll', function(){
    var docHeight = $(document).innerHeight(), 
        windowHeight = $(window).innerHeight(), 
        pageBottom = docHeight - windowHeight; 
    if(pageBottom <= $(window).scrollTop()) //{
    e.preventDefault();  // デフォルトのイベント(ページの遷移やデータ送信など)を無効にする
    $.ajax({
      url: "/mensk",  // リクエストを送信するURLを指定（action属性のurlを抽出）
      type: "POST",  // HTTPメソッドを指定（デフォルトはGET）
      datatype: "json",
      data: {
        note: null  // 送信データ
      }
    })
    .done(function(data) {
      $(".sample").append(`<div>${data}</div>`);  // HTMLを追加
      $("ul").append(`<div>{data}</div>`);
      //$("#note").val("");  // 入力欄を空にする
    })
    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
      alert("error!");  // 通信に失敗した場合の処理
      console.log("XMLHttpRequest : " + XMLHttpRequest.status);
　　console.log("textStatus     : " + textStatus);
　　console.log("errorThrown    : " + errorThrown.message);
    })
  });
//});