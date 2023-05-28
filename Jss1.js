const fs = require("fs");
// const text = fs.readFileSync("Ganesh.txt","utf-8");
console.log("Creating a new File");
const text = fs.writeFileSync("Ganesh.txt","text");
console.log("The content in the file is");
console.log(text);