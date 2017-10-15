(function () {

    fetch('rest/shoppingItems', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            shoppingGroup: 'BREAD',
            name: 'Franska',
            price: '20'
        })
    });

    fetch('rest/shoppingItems', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            shoppingGroup: 'BREAD',
            name: 'Baguette',
            price: '10'
        })
    });

    fetch('rest/shoppingItems', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            shoppingGroup: 'BREAD',
            name: 'Limpa',
            price: '10'
        })
    });

    fetch('rest/shoppingItems', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            shoppingGroup: 'FISH',
            name: 'Sammon',
            price: '60'
        })
    });

})();