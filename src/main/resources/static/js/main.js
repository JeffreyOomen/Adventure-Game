$(document).ready(function () {
    console.log("ready!");

    /* handle normal attack */
    $("#normal_attack").click(function() {
        console.log("Normal attack clicked");
        $.post("/battle/normal_attack", function(data) {
            showBattlereport(data);
        });
    });

    /* handle special attack */
    $("#special_attack").click(function() {
        $.post("/battle/special_attack", function(data) {
            showBattlereport(data);
        });
    });

    /* handle heal */
    $("#heal").click(function() {
        $.post("/battle/heal", function(data) {
            showBattlereport(data);
        });
    });

    /**
     * Show battle report the the client
     * @param data
     */
    var showBattlereport = function(data) {
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        // show battle messages
        var messageContainer = $('.messages');
        messageContainer.append($('<p "message">' + data.message + '</p>'));
        messageContainer.scrollTop(messageContainer.prop("scrollHeight"));
    };
});