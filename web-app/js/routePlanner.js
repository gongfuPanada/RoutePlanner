$(document).ready(function () {

    $(document).on("click", "#routeCalculatorButton", function() {
        $("#routeCalculatorButton").prop("disabled", true);
    });

    $(document).on("keyup", "#addresses", checkAddressesBlank);

    checkAddressesBlank();
});

function checkAddressesBlank()
{
    if ($("#addresses").val() == "") { $("#routeCalculatorButton").prop("disabled", true); }
    else { $("#routeCalculatorButton").prop("disabled", false); }
}

