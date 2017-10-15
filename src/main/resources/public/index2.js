(function () {


    function init() {
        document.addEventListener("DOMContentLoaded", function () {

            window.loginName = "Kalle";
            let searchInput = window.searchInput;
            let searchDropdown = window.searchDropDown;
            searchInput.placeAt();
            searchInput.setExportBut();
            searchDropdown.dropdown();
        });
    };

    init();


})();
