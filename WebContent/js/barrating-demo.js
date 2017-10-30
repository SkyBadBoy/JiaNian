$(function() {
    function ratingEnable() {
        $('#example-f').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
    }
    function ratingDisable() {
        $('select').barrating('destroy');
    }
    ratingEnable();
});
