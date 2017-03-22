$(document).ready(function () {
    /* handle heal */
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

            // Get first available heal potion and remove
            $('.inventory li.POTION_HEAL:first').remove();
        });
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
            losingState()
        } else if (!data.isCharacterAlive) {
            losingState();
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
        $('#battle_aftermath_btn').attr('hidden', false).attr('href', '/');
        $('#battle_aftermath_btn button').text('Home');
    }

    /**
     * Shows an alert to the player and redirects to
     * the regeneration window
     */
    function losingState() {
        $('#normal_attack').prop('disabled', true);
        $('#special_attack').prop('disabled', true);
        $('#heal').prop('disabled', true);
        $('#battle_aftermath_btn').attr('hidden', false).attr('href', '/regenerate');
        $('#battle_aftermath_btn button').text('Regenerate');
    }
});