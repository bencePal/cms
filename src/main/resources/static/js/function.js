
// toggle delete box on admin content list
$(".delete-box-trigger").click(function() {
    $(this).next().slideToggle();
});

// hide delete box on admin content list
$(".button-no-delete").click(function() {
    $(this).parent().slideUp();
});



