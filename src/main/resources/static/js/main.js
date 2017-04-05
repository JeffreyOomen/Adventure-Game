$(document).ready(function () {
    //Set CSRF Request header for all AJAX requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    });


    // Handle starting a new battle against an enemy
    $("#start_battle").click(function() {
        window.location.href = "/battle";
    });

    // Handle a normal attack action
    $("#normal_attack").click(function() {
        $.post("/battle/normalAttack", function(data) {
            battleReport(data);
            reloadBattleState();
        });
    });

    // Handle a special attack action
    $("#special_attack").click(function() {
        $.post("/battle/specialAttack", function(data) {
            battleReport(data);
            reloadInventoryFragment();
            reloadBattleState();
            disableOrEnableButton('special_attack', data.specialAttackEnabled);
        });
    });

    // Handle a heal action
    $("#heal").click(function() {
        $.post("/battle/heal", function(data) {
            battleReport(data);
            reloadInventoryFragment();
            reloadBattleState();
            disableOrEnableButton('heal', data.healAttackEnabled);
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

        var imageSrc = $("#" + id + ' img').attr('src');
        // Set selected
        $('#selectedUsableUrl').attr('src', imageSrc);


        $('#selectedUsableId').val(id); // Set id (used to delete/mount usable)
        $('#selectedItemName').text(name); // set name
        $('#selectedItemDesc').text(desc); // set desc

    });

    /**
     * Used to delete a selected usable based on id
     */
    $('#deleteUsable').click(function() {
        var id = $('#selectedUsableId').val();

        $.ajax({
            url: '/inventory',
            data: id,
            contentType: "application/text",
            dataType:"text",
            type: 'DELETE',
            success: function(result) {
                // reload page
                reloadCurrentPage();
            }
        });
    });

    /**
     * Used to use a selected usable based on id
     */
    $('#useUsable').click(function() {
        var id = $('#selectedUsableId').val();
        $.ajax({
            url: '/inventory',
            data: id,
            contentType: "application/text",
            dataType:"text",
            type: 'POST',
            success: function(result) {
                // remove item from inventory
                $('#' + id).remove();

                reloadCurrentPage();
            }
        });
    });

    /**
     * Reload current page
     */
    function reloadCurrentPage() {
        location.reload();
    }

    /**
     * Show battle report the the client
     * @param data the battle messages received from the controllers
     */

    function battleReport(data) {
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        var healButttonEnabled = data.specialAttackEnabled;
        var specialAttackButtonEnabled = data.healAttackEnabled;

        // show battle messages and automatically scroll downwards
        var messageContainer = $('.messages');
        messageContainer.append($('<p "message">' + data.message + '</p>'));
        messageContainer.scrollTop(messageContainer.prop("scrollHeight"));

        if (!data.isCharacterAlive) {
            $('#regenerate').attr('hidden', false);
            battleEndState();
            reloadInventoryFragment();
        } else if (!data.isEnemyAlive) {
            $('#go_home').attr('hidden', false);
            battleEndState();
            reloadInventoryFragment();
        }
    }

    /**
     * Disables the action buttons in the battle
     */
    function battleEndState() {
        $('#normal_attack').prop('disabled', true);
        $('#special_attack').prop('disabled', true);
        $('#heal').prop('disabled', true);
    }

    /**
     * Disables the action buttons in the battle
     */
    function battleEndState() {
        $('#normal_attack').prop('disabled', true);
        $('#special_attack').prop('disabled', true);
        $('#heal').prop('disabled', true);
    }

    function disableOrEnableButton(buttonIdName, isEnabled)  {
        console.log('disableOrEnableHealButton');

        if(isEnabled) {
            $('#' + buttonIdName).prop('disabled', false);
        } else {
            $('#' + buttonIdName).prop('disabled', true);
        }
    }


    function reloadInventoryFragment() {
        $('#fragmentInventory').load('/inventoryFragment');
    }

    function reloadBattleState() {
        $('#fragmentState').load('/battle/stateFragment');
    }
});