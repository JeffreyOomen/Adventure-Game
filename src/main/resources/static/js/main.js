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

            // Get first available heal potion and remove
            $('.inventory li.POTION_HEAL:first').remove();

        });
    });

    /* handle regenerating the character */
    $("#regenerate_character").click(function() {
        window.location.href = "/doRegenerate";
    });


    $('#inventory-page .item').click(function() {
        var id = $(this).attr('id');

        var name = $('#' + id + ' .name').text();
        var desc = $('#' + id + ' .desc').text();

        console.log('id: ' + id);
        console.log('name: ' + name);
        console.log('desc: ' + desc);

        var imageSrc = $("#" + id + ' img').attr('src');
        // Set selected
        $('#selectedUsableUrl').attr('src', imageSrc);


        $('#selectedUsableId').val(id); // Set id (used to delete/mount usable)
        // $('#selectedUsableId').text(id); // Set id (used to delete/mount usable)
        $('#selectedItemName').text(name); // set name
        $('#selectedItemDesc').text(desc); // set desc

    });

    /**
     * Used to delete a selected usable based on id
     */
    $('#deleteUsable').click(function() {
        var id = $('#selectedUsableId').val();

        console.log('Deleted item with ID: ' + id);

        $.ajax({
            url: '/inventory',
            data: id,
            contentType: "application/text",
            dataType:"text",
            type: 'DELETE',
            success: function(result) {
                // Remove item from inventory
               $('#' + id).remove();
            }
        });

    });


    /**
     * Used to delete a selected usable based on id
     */
    $('#useUsable').click(function() {
        var id = $('#selectedUsableId').val();

        console.log('Use item with ID: ' + id);

        $.ajax({
            url: '/inventory',
            data: id,
            contentType: "application/text",
            dataType:"text",
            type: 'POST',
            success: function(result) {
                // remove item from inventory
                $('#' + id).remove();

                // Get current setted item

            }
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