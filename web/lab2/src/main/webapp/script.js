let inform = '';

const graph = document.getElementById("svgGraph");
const validationMessage = document.querySelector('.message');
const table = document.getElementById("tab").getElementsByTagName("tbody")[0];


function getX() {
    return document.querySelector('input[name="x"]:checked')?.value || null;
}


function getY() {
    return document.getElementById('y').value.trim().replace(',', '.');
}

function getR() {
    const chboxes = document.querySelectorAll('input[name="r"]:checked');
    return Array.from(chboxes).map(chbox => chbox.value); // Возвращаем массив значений
}


function validateX() {
    let x = getX();
    if (x === null) {
        inform = 'Выберите значение X из предложенных.';
        return false;
    }

    const xNum = x;
    const xValues = ["-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3"];
    if (!isNaN(xNum) && xValues.includes(xNum)) {
        return true;
    } else {
        inform = 'X должно быть одним из значений: ' + xValues.join(', ');
        return false;
    }
}

function validateY() {
    let y = getY();
    const regExp = new RegExp("[^0-9.,-]");
    if (y === '' || y.match(regExp)) {
        inform = 'Введите значение Y (число с плавающей точкой).';
        console.log("Строка 45");
        return false;
    }

    const yNum = parseFloat(y);

    if (!isNaN(yNum) && yNum >= -5 && yNum <= 3) {
        return true;
    } else {
        inform = 'Y должно быть числом в диапазоне (-5, 3).';
        console.log("Строка 55");
        return false;
    }
}

function validateR() {
    const rValues = getR();
    if (rValues.length === 0) {
        inform = 'Выберите хотя бы одно значение R.';
        return false;
    }

    const validR = ["1", "2", "3", "4", "5"];
    if (rValues.every(r => validR.includes(r))) {
        return true;
    } else {
        inform = 'R должно быть одним из значений: ' + validR.join(', ');
        return false;
    }
}

function validateEverything() {
    return validateX() && validateY() && validateR();
}

const checkboxes = document.querySelectorAll('input[name="r"]');
var plus;


for (const checkbox of checkboxes) {
    checkbox.addEventListener("change", () => {
        redrawDots();
    });
}

function redrawDots() {
    const rValues = getR();
    if (rValues.length === 0) {
        document.querySelectorAll(".tmpDot").forEach(dot => dot.remove());
        return;
    }
    const max_r = Math.max(...rValues);
    document.querySelectorAll(".tmpDot").forEach(dot => dot.remove());

    const tab = document.getElementById("tab");
    if (!tab) return;

    for (let i = 1; i < tab.rows.length; i++) {
        let row = tab.rows[i];
        let x = parseFloat(row.cells[0].textContent.trim());
        let y = parseFloat(row.cells[1].textContent.trim());
        if (!isNaN(x) && !isNaN(y)) {
            addDot(x, y, max_r);
        }
    }
}

function addDot(x, y, r, hit) {
    const dot = document.createElementNS("http://www.w3.org/2000/svg", "circle");


    dot.setAttribute('cx', (x / r) * 120 + 150);
    dot.setAttribute('cy', 150 - (y / r) * 120);
    dot.setAttribute('r', '2');
    dot.setAttribute('fill', "#e74c3c");
    dot.setAttribute("class", "tmpDot");

    graph.appendChild(dot);
    console.log(`Добавлена точка: x=${x}, y=${y}, r=${r}, hit=${hit}`);
}

// Обновление таблицы
function updateTab(data) {
    console.log('Обновление таблицы:', data);
    data.forEach(result => {
        const newRow = table.insertRow();
        newRow.insertCell(0).textContent = result.x;
        newRow.insertCell(1).textContent = result.y;
        newRow.insertCell(2).textContent = result.r;
        newRow.insertCell(3).textContent = result.hit ? "Попадание" : "Промах";
        newRow.insertCell(4).textContent = result.currentTime;
        newRow.insertCell(5).textContent = result.executionTime;
    });
}

// Обработчик клика по графику
graph.addEventListener('click', function (event) {
    const rValues = getR();

    if (rValues.length === 0) {
        validationMessage.textContent = 'Невозможно определить координаты: установите значение R.';
        validationMessage.classList.add('message-fail');
        validationMessage.classList.remove('message-success');
        return;
    }

    const rect = graph.getBoundingClientRect();
    const svg = document.getElementById("svgGraph")
    let point = svg.createSVGPoint();
    point.x = event.clientX;
    point.y = event.clientY;
    let correctPoint = point.matrixTransform(svg.getScreenCTM().inverse());
    let x = correctPoint.x;
    let y = 300 - correctPoint.y;
    x -= 150;
    y -= 150;
    x = x * rValues[rValues.length - 1] / 120;
    y = y * rValues[rValues.length - 1] / 120;


    let rArr = '';
    for (i = 0; i < rValues.length; i++) {
        rArr += "&r=" + rValues[i];
    }

    const queryString = `x=${x}&y=${y}${rArr}`;



    console.log('Отправляем запрос с координатами:', {x, y, r: rValues[rValues.length - 1]});

    fetch(`http://localhost:8080/jsp-example/controller?${queryString}`, {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Ошибка ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            addDot(x, y, rValues[rValues.length-1], data.hit);
            updateTab(data);

            validationMessage.textContent = 'Точка успешно добавлена.';
            validationMessage.classList.add('message-success');
            validationMessage.classList.remove('message-fail');
        })
        .catch(error => {
            console.error('Ошибка при запросе:', error);
            validationMessage.textContent = 'Ошибка при запросе.';
            validationMessage.classList.add('message-fail');
            validationMessage.classList.remove('message-success');
        });
});

// Обработчик кнопки проверки
document.getElementById('check').addEventListener('click', function (event) {
    event.preventDefault();

    if (validateEverything()) {
        inform = 'Валидация пройдена успешно';
        validationMessage.classList.add('message-success');
        validationMessage.classList.remove('message-fail');
        validationMessage.textContent = inform;

        const xValues = getX(); // Выбранное значение X
        const y = getY();
        const r = getR();

        let rArr = '';
        for (i = 0; i < r.length; i++) {
            rArr += "&r=" + r[i];
        }

        const queryString = `x=${xValues}&y=${y}${rArr}`;

        console.log('Отправляем запрос:', queryString);

        fetch(`http://localhost:8080/jsp-example/controller?${queryString}`, {
            method: 'GET'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Ошибка ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                updateTab(data);

                // Рисуем точки на графике
                data.forEach(result => {
                    const x = parseFloat(result.x);
                    const y = parseFloat(result.y);
                    const hit = result.hit;
                    addDot(x, y, r[r.length-1], hit);
                });
            })
            .catch(error => {
                console.error('Ошибка:', error);
                validationMessage.textContent = 'Ошибка при запросе.';
                validationMessage.classList.add('message-fail');
                validationMessage.classList.remove('message-success');
            });
    } else {
        validationMessage.classList.add('message-fail');
        validationMessage.classList.remove('message-success');
        validationMessage.textContent = inform;
        console.warn('Валидация не пройдена:', inform);
    }
});
