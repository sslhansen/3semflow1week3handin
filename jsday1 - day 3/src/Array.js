/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//a
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
//b
var all = boys.concat(girls);
console.log(all);
//c
console.log(all.join(','));
console.log(all.join('-'));
//d
all.push("Lone", "Gitte");
console.log(all);
//e
all.unshift("Hans", "Kurt");
console.log(all);
//f
all.shift("Hans");
console.log(all);
//g
all.pop("Gitte");
console.log(all);
//h
all.splice(3, 2);
console.log(all);
//i
all.reverse();
console.log(all);
//j
all.sort();
console.log(all);
//k nop



//i
var newArr = all.map(n => n.toUpperCase());
console.log(newArr);
//m
//var neW = newArr.filter(x => cb1(x));
var neW = newArr.filter(x => (x.charAt(0).toUpperCase() === "L"));
console.log(neW);
function cb1(n) {
    if (n.charAt(0).toUpperCase() === "L") {
        return true;
    } else {
        return false;
    }
}
