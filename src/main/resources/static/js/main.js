$(document).ready(function () {
    /* handle heal */
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
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        // show battle messages
        var messageContainer = $('.messages');
        messageContainer.append($('<p "message">' + data.message + '</p>'));
        messageContainer.scrollTop(messageContainer.prop("scrollHeight"));

        if (!data.isCharacterAlive && !data.isEnemyAlive) {
            playerDeadAlert("You both lost...");
        } else if (!data.isCharacterAlive) {
            playerDeadAlert("You lost!");
        } else if (!data.isEnemyAlive) {
            winningState();
        }
    };

    /**
     * Disables the action buttons and shows the
     * go home button
     */
    function winningState() {
        $('#normal_attack').prop('disabled', true);
        $('#special_attack').prop('disabled', true);
        $('#heal').prop('disabled', true);
        $('#go_home').attr('hidden', false);
    }

    /**
     * Shows an alert to the player and redirects to
     * the regeneration window
     * @param message the message to be shown in the alert box
     */
    function playerDeadAlert(message) {
        alert(message);
        window.location.href = "/regenerate";
    }
});