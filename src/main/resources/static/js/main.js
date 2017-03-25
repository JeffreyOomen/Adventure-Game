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

<<<<<<< HEAD
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
        // $('#selectedUsableId').text(id); // Set id (used to delete/mount usable)
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

=======
>>>>>>> a97479f64a2eac4d5a6b60bdb4fe60336a5a08df
    /**
     * Show battle report the the client
     * @param data the battle messages received from the controllers
     */
<<<<<<< HEAD
    var showBattlereport = function(data) {
=======
    function battleReport(data) {
>>>>>>> a97479f64a2eac4d5a6b60bdb4fe60336a5a08df
        $('#charCurrentHp').html(data.characterStats.currentHitpoints);
        $('#enemyCurrentHp').html(data.enemyStats.currentHitpoints);

        // show battle messages and automatically scroll downwards
        var messageContainer = $('.messages');
        messageContainer.append($('<p "message">' + data.message + '</p>'));
        messageContainer.scrollTop(messageContainer.prop("scrollHeight"));

        if (!data.isCharacterAlive) {
            $('#regenerate').attr('hidden', false);
            battleEndState();
        } else if (!data.isEnemyAlive) {
            $('#go_home').attr('hidden', false);
            battleEndState();
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
});