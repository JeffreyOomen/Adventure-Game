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


                var charCurrentHp = data.characterStats.currentHitpoints;
                $('#charCurrentHp').html(charCurrentHp);

                var ememyCurrentHp = data.enemyStats.currentHitpoints;
                $('#enemyCurrentHp').html(ememyCurrentHp);

                var message = data.message;
                var newMessageDiv = $('<p "message">' + message + '</p>')

                var messageContainer = $('.messages');
                messageContainer.append(newMessageDiv);
                messageContainer.scrollTop(messageContainer.prop("scrollHeight"));
            });

    });
});