$('.reply-msg').on("click", function () {


    $('#toEmail').val($(this).attr("data-email"));

    $('#emailModal').modal('show');

});