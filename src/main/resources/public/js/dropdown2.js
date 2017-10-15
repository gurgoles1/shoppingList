(function () {
    const self = {};
    let shoppingItems;
    let loginName = window.loginName;

    self.dropdown = function () {
        let inputSearch = document.getElementById("searchInput");
        let tablebody = document.getElementById("autofilledtable");

        let noInput = new CustomEvent("noInput");

        inputSearch.addEventListener("noInput", function () {
            tablebody.innerHTML = "";
        });
        addInputEventListener(inputSearch, tablebody, noInput);

    }

    function addClickListenerToDropdownItem(elementByName, filteredArray) {
        elementByName.addEventListener("click", function () {
            let shoppingItem = filteredArray[elementByName.id];
            postItemToShoppingList(shoppingItem.shoppingGroup, shoppingItem.name, shoppingItem.price, loginName).then(function () {

                fetchYourShoppingList(loginName);
            });

        });
    }

    function addInputEventListener(inputSearch, tablebody, noInput) {
        inputSearch.addEventListener("input", function () {
            tablebody.innerHTML = "";

            console.log(Array.isArray(shoppingItems));

            let filteredArray = shoppingItems.filter(function (val) {
                let name = val.name.toLowerCase();
                let inputSearchValue = inputSearch.value.toLowerCase();
                //Om detta stämmer för det värdet i shoppingItems så läggs det i filteredArray
                return name.startsWith(inputSearchValue);
            });

            for (let i = 0; i < filteredArray.length; i++) {

                tablebody.innerHTML = tablebody.innerHTML + `<tr>
                    <td>
                        <a id=${i} style="cursor: pointer" class="addItem">${filteredArray[i].name}</a>
                   </td>
                </tr>`;
            }

            //Vill posta till backend när trycker för att registrera att man valt.
            //Sedan ska backend hålla holl på hur många och ge det.
            let elementsByName = document.getElementsByClassName("addItem");

            for (let i = 0; i < elementsByName.length; i++) {
                addClickListenerToDropdownItem(elementsByName[i], filteredArray);
            }

            if (inputSearch.value === "")
                inputSearch.dispatchEvent(noInput);

        });
    }

    function fetchYourShoppingList(name) {
        fetch('rest/shoppingList/' + name).then(function (res) {
            res.json().then(function (shoppingListData) {
                let ownShoppingList;
                ownShoppingList = shoppingListData;
                let elementById = document.getElementById("shoppingList");
                elementById.innerHTML = "";
                for (let i = 0; i < ownShoppingList.stuffToBuyAndNumberOfThem.length; i++) {
                    let shoppingItem = ownShoppingList.stuffToBuyAndNumberOfThem[i];
                    console.log(shoppingItem.itemForSale.name)
                    elementById.innerHTML = elementById.innerHTML + `<div>${shoppingItem.itemForSale.name} ${shoppingItem.quantity}st</div>`;
                }
            });
        });
    }

    function postItemToShoppingList(shoppingGroup, name, price, loginName) {
        return fetch('rest/shoppingItem/' + loginName, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                itemForSale: {
                    shoppingGroup: shoppingGroup,
                    name: name,
                    price: price
                },
                quantity: '1'
            })
        });

    }

    (function fetchAllListItem() {
        fetch('rest/shoppingItems')
            .then(function (res) {
                res.json().then(function (data) {
                    shoppingItems = data;
                });
            });
    })();

    window.searchDropDown = self;

})();



