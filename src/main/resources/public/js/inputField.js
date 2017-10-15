//Blir closures när man exponerar objektet och sedan använder objektet för att sätta det man vill exponera respektive inte vill.
(function () {

    const self = {},
        template = `<div>
            <input id="searchInput" type="text" />
            <i class="glyphicon glyphicon-remove"></i>
            <table>
                <tbody id="autofilledtable">
                </tbody>
            </table>
        </div>
        <div>
            <button id="exportListToText" class="btn btn-primary btn-mini search-bar-btn">Export</button>
        </div>
        <div id="searchBut">
            <button type="button" class="btn btn-primary btn-mini search-bar-btn">Search</button>
        </div>
        <div id="remove">`;

    let exportBut;
    let data;


    self.placeAt = function () {
        document.getElementById("search").innerHTML = template;
        exportBut = document.getElementById("exportListToText");
    };

    function setExportBut() {
        exportBut.addEventListener("click", function () {
            //Här är promisen (exportPromise blockerande, men inte resterande then)
            exportPromise();
        });
    }

    let exportPromise = function () {

        window.open('http://localhost:8080/export.docx/' + window.loginName + "s" + "shoppingList");



        /*    post();
         //Brukar kapsla in Promise i en funktion som inte tar några parametrar och retunerar en promise
         return new Promise(function (resolve, reject) {

         fetchShoppingItems().then(function () {
         //kanske inte behövs och är onödigt om det tar för långt tid att ge data sitt värde
         if (data.status == 200) {
         resolve();
         }
         else {
         reject();
         }
         });
         });*/
    }

    function post() {
        fetch('rest/shoppingItems', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                shoppingGroup: 'FISH',
                name: 'sammon',
                price: '60'
            })
        });
    }

    function fetchShoppingItems() {
        //fetch retunerar en promise.
        //Denna är blockerande tills fetch är klar. Detta inkluderar inte
        //de efterkommande then

        return fetch('rest/shoppingItems')
            .then(function (res) {
                data = res;
            });
    }

    function writeSomething(something) {
        this.hej = something;
        console.log(this);
    }

    //apply är som call men en lista med argument
    writeSomething.call(self, "HEJ");

    self.setExportBut = setExportBut;
    window.searchInput = self;

})();


// Post example
// fetch('rest/shoppingItems', {
//     method: 'POST',
//     headers: {
//         'Content-Type': 'application/json'
//     },
//     body: JSON.stringify({
//             shoppingGroup: 'FISH',
//             name: 'sammon',
//             price: '60'
//         })
// });

//Iterator example
//let hej = [1,2,3,4,5];
//let hejIterator = hej[Symbol.iterator]();
//hejIterator.next() returnerar objekt med värdet och boolean om iterator är slut