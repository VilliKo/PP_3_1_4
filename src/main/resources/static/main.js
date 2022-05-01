/**
 *
 */

$('document').ready(function(){

    $('.table .btn').on('click',function(event) {

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(user, status) {
            $('#IdEdit').val(user.id);
            $('#firstnameEdit').val(user.firstname);
            $('#lastnameEdit').val(user.lastname);
            $('#ageEdit').val(user.age);
            $('#usernameEdit').val(user.username);
            $('#passwordEdit').val(user.password);
            $('#rolesEdit').val(user.roles);
        })

        $('#editModal').modal();
    });
});