// Common functions used with forms

// We use this as a hash to track those elements validated on a per element
// basis that have formatting problems
validateResults = new Object();
validateNames = new Object();
validateProblems = new Object();

// Takes a value, checks if it's a float, and returns true or false
function isFloat(value) {
  return value == parseFloat(value);
}

// Takes a value, checks if it's an integer, and returns true or false
function isInteger(value) {
  return value == parseInt(value);
}

// Takes a value and a range, checks if the value is in the range, and
// returns true or false
function inRange(value, low, high) {
  return value >= low && value <= high ;
}

// Checks values against formats such as '#####' or '###-##-####'
function checkFormat(value, format) {
  if(value.length != format.length)
    return false;
  for(var i = 0; i < format.length; i++)
    if(format.charAt(i) == '#' && !isInteger(value.charAt(i)))
      return false;
    else if(format.charAt(i) != '#' && format.charAt(i) != value.charAt(i))
      return false;
  return true;
}

// Takes a form and an array of element names; verifies that each has a value
function requireValues(form, requiredElementNames, displayNames) {
  for(var i = 0; i < requiredElementNames.length; i++) {
    var name = requiredElementNames[i];
    var displayName = displayNames[i];
    if(form[name].value == "") {
      alert("Заполните, пожалуйста, обязательное поле \"" +
            displayName + "\".");
      return false;
    }
  }
  return true;
}

// Takes a form and an array of element names; verifies that each has an
// option selected (other than the first; assumes that the first option in
// each select menu contains instructions)
function requireSelects(form, requiredSelects, displayNames) {
  for(var i = 0; i < requiredSelects.length; i++) {
    var name = requiredSelects[i];
    var displayName = displayNames[i];
    if(form[name].selectedIndex <= 0) {
      alert("Выберите, пожалуйста, значение элемента \"" +
            displayName + "\".");
      return false;
    }
  }
  return true;
}

// Takes a form and an array of element names; verifies that each has a
// value checked
function requireRadios(form, requiredRadios, displayNames) {
  for(var i = 0; i < requiredRadios.length; i++) {
    var name = requiredRadios[i];
    var displayName = displayNames[i];
    var isChecked = false;
    for(var j = 0; j < form[element].length; j++)
      if(form[name][j].checked)
        isChecked = true;
    if(!isChecked) {
      alert("Выберите, пожалуйста, одно из значений элемента \"" +
            displayName + "\".");
      return false;
    }
  }
  return true;
}

// Verify there aren't any uncorrected formatting problems with elements
// validated on a per element basis
function checkProblems() {
  for(var name in validateResults)
    if(!validateResults[name]) {
      alert("Исправьте, пожалуйста, значение элемента \"" +
            validateNames[name] + "\"." +
            "\n" + validateProblems[name] + ".");
      return false;
    }
  return true;
}

// Verifies that the value of the provided element has ##### format
function checkTime(element, displayName) {
  validateProblems[element.name] = "Время должно быть в формате 23:35";
  validateNames[element.name] = displayName;
  var result = true;
  if(element.value != "" && !checkFormat(element.value, "##:##"))
    result = false;
  validateResults[element.name] = result;
  return true;
}

function checkDate(element, displayName) {
  validateProblems[element.name] = "Дата должна быть в формате 17.07.1980";
  validateNames[element.name] = displayName;
  var result = true;
  if(element.value != "" && !checkFormat(element.value, "##.##.####"))
    result = false;
  validateResults[element.name] = result;
  return true;
}

function checkPrice(element, displayName) {
  validateProblems[element.name] = "Цена должна быть положительным" +
                                   " вещественным числом с не более чем двумя" +
                                   " знаками после десятичной точки";
  validateNames[element.name] = displayName;
  var result = isFloat(element.value);
  result &= element.value > 0.00;
  result |= element.value == "";
  validateResults[element.name] = result;
  return true;
}

function checkCreditCard(element, displayName) {
  validateProblems[element.name] =
      "Номер банковской карты должен состоять из 20 цифр";
  validateNames[element.name] = displayName;
  var result = true;
  if(element.value != "" && !checkFormat(element.value, "####################"))
    result = false;
  validateResults[element.name] = result;
  return true;
}

function checkPlaces(element, displayName, freePlaces) {
  validateProblems[element.name] =
      "Количество мест должно быть положительным целым числом.\n" +
      "Заказанное количество мест не должно превышать количество\n" +
      "свободных мест выбранного класса";
  validateNames[element.name] = displayName;
  var result = isInteger(element.value);
  result &= inRange(element.value, 1, freePlaces);
  result |= element.value == "";
  validateResults[element.name] = result;
  return true;
}

function checkLinked(form, name1, displayName1,
                     name2, displayName2, commonName) {
  validateProblems[commonName] =
      "Значения \"" + displayName1 + "\" и \"" +
      displayName2 + "\" должны быть заполнены вместе";
  validateNames[commonName] = commonName;
  var result = true;
  if((form[name1].value != "" && form[name2].value == "") ||
     (form[name2].value != "" && form[name1].value == ""))
    result = false;
  validateResults[commonName] = result;
  return true;
}
