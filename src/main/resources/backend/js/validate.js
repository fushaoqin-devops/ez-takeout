
function isValidUsername (str) {
  return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal (path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone (val) {
  return /(^\([2-9]\d{2}\)\s\d{3}-\d{4}$)/.test(val);
}

//Validate username
function checkUserName (rule, value, callback){
  if (value === "") {
    callback(new Error("Please enter username"))
  } else if (value.length > 20 || value.length <3) {
    callback(new Error("Username should be 3-20 characters"))
  } else {
    callback()
  }
}

//Validate name
function checkName (rule, value, callback){
  if (value === "") {
    callback(new Error("Please enter your name"))
  } else if (value.length > 12) {
    callback(new Error("Name should be less than 12 characters"))
  } else {
    callback()
  }
}

//Validate phone number
function checkPhone (rule, value, callback){
  if (value === "") {
    callback(new Error("Please enter phone number"))
  } else if (!isCellPhone(value)) {
    callback(new Error("Please enter valid phone number!"))
  } else {
    callback()
  }
}


function validID (rule,value,callback) {
  // regex for SSN
  let reg = /(^\d{3}-\d{2}-\d{4}$)/
  if(value === '') {
    callback(new Error('Please enter your social security number'))
  } else if (reg.test(value)) {
    callback()
  } else {
    callback(new Error('Invalid SSN'))
  }
}