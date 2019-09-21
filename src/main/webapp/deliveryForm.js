/* 
 * JS file for the Pizza Order Application.
 * This script checks if there is a change to the "delivery method" drop-down
 * box and executes functions based on selected option.
 * If delivery is selected, form is populated with delivery details and pizza 
 * details entry fields
 * If pick-up is selected, form is populated with pizza deteils entry fields
 */

window.onload = function () {
    //Add onchange listener to delivery method drop-down
    document.getElementById("delMethod").onchange = function () {
        var form = document.getElementById("delMethod");
        var method = form.options[form.selectedIndex].value;
        //Action correct function based on selection
        if (method === "delivery") {
            clearForm();
            displayDeliveryForm();
        } else if(method === "pickUp") {
            clearForm();
            displayPizzaForm();
        }
    };
};

/**
 * Function creates input fields for delivery details and appends them to 
 * form in HTML
 */
function displayDeliveryForm() {
    var form = document.getElementById("formDiv");
    
    //Delivery details heading
    var heading = document.createElement("H2");
    var text = document.createTextNode("Delivery details:");
    heading.appendChild(text);
    form.appendChild(heading);
    
    //Street address entry
    var streetAddress = document.createElement("input"); 
    streetAddress.setAttribute('type', "text");
    streetAddress.setAttribute('name', "streetAddress");
    
    var streetAddressLabel = document.createElement("Label");
    streetAddressLabel.htmlFor = "streetAddress";
    streetAddressLabel.innerHTML = "Street Address ";
    
    form.appendChild(streetAddressLabel);
    form.appendChild(streetAddress);
    form.appendChild(getBr());
    form.appendChild(getBr());

    //City entry
    var city = document.createElement("input");
    city.setAttribute('type', "text");
    city.setAttribute('name', "city");
    
    var cityLabel = document.createElement("Label");
    cityLabel.htmlFor = "city";
    cityLabel.innerHTML = "City ";

    form.appendChild(cityLabel);
    form.appendChild(city);
    form.appendChild(getBr());
    form.appendChild(getBr());
    
    //Postal code entry
    var postal = document.createElement("input"); 
    postal.setAttribute('type', "text");
    postal.setAttribute('name', "postal");
    
    var postalLabel = document.createElement("Label");
    postalLabel.htmlFor = "postal";
    postalLabel.innerHTML = "Postal code ";

    form.appendChild(postalLabel);
    form.appendChild(postal);
    
    form.appendChild(getBr());
    form.appendChild(getBr());
    form.appendChild(getBr());
    
    //Pizza details heading
    var headingPizza = document.createElement("H2");
    var textPizza = document.createTextNode("Create your pizza:");
    headingPizza.appendChild(textPizza);
    form.appendChild(headingPizza);
    
    //Populate form with pizza details entry fields
    displayPizzaForm();
}

/**
 * Function creates input fields for pizza details and appends them to 
 * form in HTML
 */
function displayPizzaForm() {
    var form = document.getElementById("formDiv");
    
    //Radio button selection for pizza size
    var smallRadio = document.createElement("input");
    smallRadio.setAttribute('type', "radio");
    smallRadio.setAttribute('name', "size");
    smallRadio.setAttribute('value', "small");
    smallRadio.setAttribute('checked', "checked");
    
    form.appendChild(smallRadio);
    
    var smallRadioLabel = document.createElement("Label");
    smallRadioLabel.htmlFor = "smallRadio";
    smallRadioLabel.innerHTML = " Small ($5)";
    
    form.appendChild(smallRadioLabel);
    
    var mediumRadio = document.createElement("input");
    mediumRadio.setAttribute('type', "radio");
    mediumRadio.setAttribute('name', "size");
    mediumRadio.setAttribute('value', "medium");
    
    form.appendChild(mediumRadio);
    
    var mediumRadioLabel = document.createElement("Label");
    mediumRadioLabel.htmlFor = "mediumRadio";
    mediumRadioLabel.innerHTML = " Medium ($7)";
    
    form.appendChild(mediumRadioLabel);
    
    var largeRadio = document.createElement("input");
    largeRadio.setAttribute('type', "radio");
    largeRadio.setAttribute('name', "size");
    largeRadio.setAttribute('value', "large");
    
    form.appendChild(largeRadio);
    
    var largeRadioLabel = document.createElement("Label");
    largeRadioLabel.htmlFor = "largeRadio";
    largeRadioLabel.innerHTML = " Large ($9)";
    
    form.appendChild(largeRadioLabel);
    form.appendChild(getBr());
    form.appendChild(getBr());
    
    //Checkbox selection for toppings
    var pepperoniCheck = document.createElement("input");
    pepperoniCheck.setAttribute('type', "checkbox");
    pepperoniCheck.setAttribute('name', "topping");
    pepperoniCheck.setAttribute('value', "pepperoni");
    
    form.appendChild(pepperoniCheck);
    
    var pepperoniCheckLabel = document.createElement("Label");
    pepperoniCheckLabel.htmlFor = "pepperoniCheck";
    pepperoniCheckLabel.innerHTML = " Pepperoni";
    
    form.appendChild(pepperoniCheckLabel);
    form.appendChild(getBr());
    
    var sausageCheck = document.createElement("input");
    sausageCheck.setAttribute('type', "checkbox");
    sausageCheck.setAttribute('name', "topping");
    sausageCheck.setAttribute('value', "sausage");
    
    form.appendChild(sausageCheck);
    
    var sausageCheckLabel = document.createElement("Label");
    sausageCheckLabel.htmlFor = "sausageCheck";
    sausageCheckLabel.innerHTML = " Sausage";
    
    form.appendChild(sausageCheckLabel);
    form.appendChild(getBr());
    
    var spinachCheck = document.createElement("input");
    spinachCheck.setAttribute('type', "checkbox");
    spinachCheck.setAttribute('name', "topping");
    spinachCheck.setAttribute('value', "spinach");
    
    form.appendChild(spinachCheck);
    
    var spinachCheckLabel = document.createElement("Label");
    spinachCheckLabel.htmlFor = "spinachCheck";
    spinachCheckLabel.innerHTML = " Baby Spinach";
    
    form.appendChild(spinachCheckLabel);
    form.appendChild(getBr());
    
    var pepperCheck = document.createElement("input");
    pepperCheck.setAttribute('type', "checkbox");
    pepperCheck.setAttribute('name', "topping");
    pepperCheck.setAttribute('value', "pepper");
    
    form.appendChild(pepperCheck);
    
    var pepperCheckLabel = document.createElement("Label");
    pepperCheckLabel.htmlFor = "pepperCheck";
    pepperCheckLabel.innerHTML = " Pepper";
    
    form.appendChild(pepperCheckLabel);
    form.appendChild(getBr());
    form.appendChild(getBr());
    
    //Submit button
    var submitDiv = document.createElement("div");
    submitDiv.setAttribute('class', "buttonHolder");
    
    form.appendChild(submitDiv);
    
    var submit = document.createElement("input");
    submit.setAttribute('type', "submit");
    submit.setAttribute('value', "Place My Order");
    
    submitDiv.appendChild(submit);
    
}

/**
 * Function creates and returns new <br> element
 */
function getBr() {
    var br = document.createElement('br');
    return br;
}

/**
 * Function clears all children from the form <div>
 */
function clearForm() {
    
    var form = document.getElementById("formDiv");
    while (form.firstChild) {
        form.removeChild(form.firstChild);
    }
}