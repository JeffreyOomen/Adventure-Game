$(document).ready(function () {

    //Set CSRF Request header for all AJAX requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    //Hook your headers here and set it with before send function.
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    });

    $("#start_battle").click(function() {
        window.location.href = "/battle";
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

        if (!data.isCharacterAlive && !data.isEnemyAlive) {
            alert("You both lost...");
        } else if (!data.isCharacterAlive) {
            alert("You lost!");
        } else if (!data.isEnemyAlive) {
            window.location.href = "/battle/end";
            alert("You won!");
        }
    };
});