/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("delMethod").onchange = function() {
    var form = document.getElementById("delMethod");
    var method = form.options[form.selectedIndex].value;
    console.log(method);
};

