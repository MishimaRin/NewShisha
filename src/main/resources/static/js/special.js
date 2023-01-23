$(function(){
  $("#toggle").on("click", function() {
    $(".toggle-list").slideToggle();
  });
});

$(function(){
    var winScrollTop;
    $('.js-modal-open').each(function(){
        $(this).on('click',function(i){
            //スクロール位置を取得
            winScrollTop = $(window).scrollTop();
             
            var target = $(this).data('target');
            var modal = document.getElementById(target);
            $(modal).fadeIn();
            return false;
        });
    });
    $('.js-modal-close').on('click',function(){
        $('.js-modal').fadeOut();
        $('body,html').stop().animate({scrollTop:winScrollTop}, 100);
        return false;
    }); 
});
