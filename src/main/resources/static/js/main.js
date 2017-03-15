$(document).ready(function () {
    console.log("ready!");

    $(".inputbutton").click(function () {


        var attackType = $(this).val();
        console.log(attackType);

        $.post("/battle",
            {
                attackType: attackType,
            },
            function (data, status) {
                console.log(data);
            });

        
    });
});