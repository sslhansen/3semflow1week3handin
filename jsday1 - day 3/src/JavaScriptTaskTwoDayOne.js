/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Observe: no return type, no type on parameters
function add(n1, n2) {
    return n1 + n2;
}


var sub = function (n1, n2) {
    return n1 - n2;
};


var cb = function (n1, n2, callback) {
    try {
        if (typeof n1 !== "number" || typeof n2 !== "number" || typeof callback !== "function") {
            throw new Error("You entered a wrong type!");
        } else {
            return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
        }
    } catch (e) {
        console.error(e.name + " " + e.message);
    }
};

//2
console.log(add(1, 2));     // What will this print? - 3
console.log(add);          // What will it print and what does add represent? - it will just call the function and write "add".
console.log(add(1, 2, 3)); // What will it print - it will print 3
console.log(add(1));	  // What will it print - it will print something write something wrong
console.log(cb(3, 3, add)); // What will it print - it will print result from the numbers = 3+3=6
console.log(cb(4, 3, sub)); // What will it print - it will print result from the numbers = 4 + 3 = 6
//console.log(cb(3, 3, add())); // What will it print (and what was the problem) - it will try to use the result of add() as a callback
console.log(cb(3, "hh", add)); // What will it print - it will print 3 + hh = 3hh

//3
console.log(cb(1, "juice", add));

//4
function mul(n1, n2) {
    return n1 * n2;
}
//5
console.log(cb(4, 2, function () {
    return 4 / 2;
}));
//                          NEW SECTION GETTING COMFY


//1
var names = ["Lars", "Jan", "Peter", "Svend", "Bo"];
var shortNames = names.filter(x => x.length <= 3);
names.forEach(x => console.log(x));
shortNames.forEach(x => console.log(x));
//2
var shortNamesCaps = shortNames.map(x => x.toUpperCase());
var namesCaps = names.map(x => x.toUpperCase());
console.log(shortNamesCaps);
console.log(namesCaps);
//3
var namesForHtml = ["Lars", "Peter", "Jan", "Ian"];
console.log(tohtml(namesForHtml));
function tohtml(a) {
    return "<ul>" + a.map(x => "<li>" + x + "</li>").join("") + "</ul>";
}

//4
var cars = [
    {id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000},
    {id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900},
    {id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000},
    {id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799},
    {id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799}
];
var volvos = cars.filter(x => (x.make === "Volvo"));
var newCars = cars.filter(x => (x.year > 1999));
var cheapCars = cars.filter(x => (x.price < 5000));
console.log(newCars);
console.log(volvos);
console.log(cheapCars);

//4a
cars.forEach(x => console.log(generateSqlInsert("cars", x)));

function generateSqlInsert(tableName, obj) {
    var attrArray = Object.getOwnPropertyNames(obj);
    var strArray = new Array();
    for (var property in obj) {
        strArray.push(obj[property]);
    }
    return "INSERT INTO " + tableName + "(" + attrArray.join(",") + ") VALUES (" + strArray + ");";
}