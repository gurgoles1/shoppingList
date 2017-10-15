//Gör på första barnet bara eftersom annars tar det bort texten oxå
$().ready(function () {

    $("[class*='span']").hover(function () {
            $(this).children().eq(1).css("display", "block");
        },
        function () {
            $(this).children().eq(1).css("display", "none")
        });



});

