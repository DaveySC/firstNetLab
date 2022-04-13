$(function () {
    $('#addButton').click(function () {
        $('.modal').addClass('modal_active');
    });

    $('.modal__close-button').click(function () {
        $('.modal').removeClass('modal_active');
    });
});


$(function () {
    $('.changeButton').click(function () {
        $('.modal1').addClass('modal_active');
    });

    $('.modal__close-button1').click(function () {
        $('.modal1').removeClass('modal_active');
    });
});


$(function () {
    $('.deleteButton').click(function () {
        $('.modal2').addClass('modal_active');
    });

    $('.modal__close-button2').click(function () {
        $('.modal2').removeClass('modal_active');
    });
});

function updateData(e){
    var $item = $(e).closest("tr")
        .find("td:first")
        .text();
    console.log($item);
}
