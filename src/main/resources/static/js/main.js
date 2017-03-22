$(document).ready(function () {

    // Handle starting a new battle against an enemy
    $("#start_battle").click(function() {
        window.location.href = "/battle";
    });

    // Handle a normal attack action
    $("#normal_attack").click(function() {
        $.post("/battle/normalAttack", function(data) {
            battleReport(data);
        });
    });

    // Handle a special attack action
    $("#special_attack").click(function() {
        $.post("/battle/specialAttack", function(data) {
            battleReport(data);
        });
    });

    // Handle a heal action
    $("#heal").click(function() {
        $.post("/battle/heal", function(data) {
            battleReport(data);

            // Get first available heal potion and remove
            $('.inventory li.POTION_HEAL:first').remove();
        });
    });

    /**
     * Show battle report the the client
     * @param data the battle messages received from the controllers
     */
    function battleReport(data) {
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        // show battle messages and automatically scroll downwards
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
    }

    /**
     * Shows a button to the user which can be used to navigate back to the regeneration screen
     */
    function winningState() {
        $('#battle_aftermath_btn').attr('hidden', false).attr('href', '/');
        $('#battle_aftermath_btn button').text('Home');
        battleEndState();
    }

    /**
     * Shows a button to the user which can be used to navigate back to the home screen
     */
    function losingState() {
        $('#battle_aftermath_btn').attr('hidden', false).attr('href', '/regenerate');
        $('#battle_aftermath_btn button').text('Regenerate');
        battleEndState();
    }

    /**
     * Disables the action buttons in the battle
     */
    function battleEndState() {
        $('#normal_attack').prop('disabled', true);
        $('#special_attack').prop('disabled', true);
        $('#heal').prop('disabled', true);
    }
});