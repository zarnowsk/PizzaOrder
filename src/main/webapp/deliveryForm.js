/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
    document.getElementById("delMethod").onchange = function () {
        var form = document.getElementById("delMethod");
        var method = form.options[form.selectedIndex].value;
        if (method === "delivery") {
            displayForm();
        }
    };
};

function displayForm() {
    var form = document.getElementById("formDiv");
    while (form.firstChild) {
        form.removeChild(form.firstChild);
    }

    var streetAddress = document.createElement("input"); //input element, text
    streetAddress.setAttribute('type', "text");
    streetAddress.setAttribute('name', "streetAddress");
    
    var streetAddressLabel = document.createElement("Label");
    streetAddressLabel.htmlFor = "streetAddress";
    streetAddressLabel.innerHTML = "Street Address ";
    
    form.appendChild(streetAddressLabel);
    form.appendChild(streetAddress);
    form.appendChild(getBr());

    var city = document.createElement("input"); //input element, text
    city.setAttribute('type', "text");
    city.setAttribute('name', "city");
    
    var cityLabel = document.createElement("Label");
    cityLabel.htmlFor = "city";
    cityLabel.innerHTML = "City ";

    form.appendChild(cityLabel);
    form.appendChild(city);
    form.appendChild(getBr());
    
    var postal = document.createElement("input"); //input element, text
    postal.setAttribute('type', "text");
    postal.setAttribute('name', "postal");
    
    var postalLabel = document.createElement("Label");
    postalLabel.htmlFor = "postal";
    postalLabel.innerHTML = "Postal ";

    form.appendChild(postalLabel);
    form.appendChild(postal);

}

function getBr() {
    var br = document.createElement('br');
    return br;
}

