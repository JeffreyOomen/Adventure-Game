$(document).ready(function () {

    //Set CSRF Request header for all AJAX requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    });

    $("#start_battle").click(function() {
        window.location.href = "/start";
    });

    /* handle normal attack */
    $("#normal_attack").click(function() {
        $.post("/battle/normalAttack", function(data) {
            showBattlereport(data);
        });
    });

    /* handle special attack */
    $("#special_attack").click(function() {
        $.post("/battle/specialAttack", function(data) {
            showBattlereport(data);
        });
    });

    /* handle heal */
    $("#heal").click(function() {
        $.post("/battle/heal", function(data) {
            showBattlereport(data);

            // Get first available heal potion and remove
            $('.inventory li.POTION_HEAL:first').remove();

        });
    });

    /* handle regenerating the character */
    $("#regenerate_character").click(function() {
        window.location.href = "/doRegenerate";
    });

    /**
     * Show battle report the the client
     * @param data
     */
    var showBattlereport = function(data) {
        console.log(data);
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        // show battle messages
        var messageContainer = $('.messages');
        messageContainer.append($('<p "message">' + data.message + '</p>'));
        messageContainer.scrollTop(messageContainer.prop("scrollHeight"));

        if (!data.isCharacterAlive && !data.isEnemyAlive) {
            alert("You both lost...");
        } else if (!data.isCharacterAlive) {
            window.location.href = "/regenerate";
            alert("You lost!");
        } else if (!data.isEnemyAlive) {
            window.location.href = "/quit";
            alert("You won!");
        }
    };
});