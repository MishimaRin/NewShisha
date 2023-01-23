// スクロールされた時に実行
$(window).on("scroll", function () {
　　// スクロール位置
    var document_h = $(document).height();              
    var window_h = $(window).height() + $(window).scrollTop();    
    var scroll_pos = (document_h - window_h) / document_h ;   
        
    // 画面最下部にスクロールされている場合
    if (scroll_pos <= 1) {
        // ajaxコンテンツ追加処理
        ajaxAddContent()
    }
});
 
// ajaxコンテンツ追加処理
function ajax_add_content() {
    // 追加コンテンツ
    var add_content = "";
    // コンテンツ件数           
    var count = $("#count").val();
     
    // ajax処理
    $.post({
        type: "post",
        datatype: "json",
        url: "/mensk",
        data:{ 
	count : $("#count").length 
	}
    }).done(function(data){
        // コンテンツ生成
        $.each(data,function(key, val){
            add_content += "<div>"+val.content+"</div>";
        })
        // コンテンツ追加
        $("#content").append(add_content);
        // 取得件数を加算してセット
        count += data.length
        $("#count").val(count);
    }).fail(function(e){
        console.log(e);
    })
}