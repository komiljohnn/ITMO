document.getElementById('check').addEventListener('click', (e) => {
    e.preventDefault();

    const x = document.querySelector('input[name="x"]:checked')?.value || null;
    const y = document.getElementById('y').value.trim();
    const selectedButton = document.querySelector('.form_r-btn.selected');
    const r = selectedButton ? selectedButton.dataset.value : null;

    console.log(x , y, r);
    if (!check(x, y, r)) {
        return;
    }

    fetch(`http://localhost:8080/fcgi-bin/server.jar?x=${x}&y=${y.replace(",", ".")}&r=${r}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })

        .then(response => response.json())
        .then(parsedRes => {
            addRowToTable(parsedRes);
            saveResultToLocalStorage(parsedRes);
            //updatePointPosition(parsedRes);
        })
        .catch(error => {
            console.error("Ошибка при обработке ответа сервера:", error);
            document.getElementById("input-log").innerText = 'Ошибка при обработке ответа сервера';
        });
});

document.querySelectorAll('.form_r-btn').forEach(button => {
    button.addEventListener('click', (e) => {
        e.preventDefault();
        document.querySelectorAll('.form_r-btn').forEach(btn => btn.classList.remove('selected'));
        button.classList.add('selected');
    });
});

function saveResultToLocalStorage(res) {
    const results = JSON.parse(localStorage.getItem("results") || "[]");
    results.push(res);
    localStorage.setItem("results", JSON.stringify(results));
}

window.addEventListener("load", () => {
    const savedResults = JSON.parse(localStorage.getItem("results") || "[]");
    savedResults.forEach(res => addRowToTable(res));
});

function addRowToTable(res) {
    const tbody = document.querySelector(".js-table");
    const row = document.createElement("tr");
    const rowIndex = tbody.rows.length + 1;

    row.innerHTML = `
        <td>${rowIndex}</td>
        <td>${res.x}</td>
        <td>${res.y}</td>
        <td>${res.r}</td>
        <td>${res.result === "true" ? "Точно в цель" : "Промах"}</td>
        <td>${res.time}</td>
        <td>${res.workTime}</td>
    `;
    tbody.appendChild(row);
}

document.getElementById('clear-storage').addEventListener('click', () => {
    localStorage.removeItem("results");
    const tbody = document.querySelector(".js-table");
    tbody.innerHTML = "";
});

let log = document.getElementById('input-log');

function check(x, y, r) {
    log.innerHTML = "";
    log.classList.remove("success");

    if (validateX(x) && validateY(y) && validateR(r)) {
        showMessage("Все данные корректны!", true);
        return true;
    }
    return false;
}

function validateX(x) {
    if (!x) {
        showMessage('Выберите значение для X');
        return false;
    }

    const parsedX = parseFloat(x);
    if ([-4,-3,-2,-1,0,1,2,3,4].includes(parsedX)) {
        return true;
    } else {
        showMessage('Введите корректное значение для X в диапазоне от -4 до 4');
        return false;
    }
}

function validateY(y) {
    if (!y || y.trim() === "") {
        showMessage('Введите значение для Y');
        return false;
    }

    // Заменяем все запятые на точки
    const normalizedY = y.replace(",", ".");

    // Преобразуем строку в число
    const parsedY = parseFloat(normalizedY);

    // Проверяем, является ли значение числом в диапазоне от -5 до 5
    if (!isNaN(parsedY) && parsedY >= -5 && parsedY <= 5) {
        return true;
    } else {
        showMessage('Введите корректное значение для Y в диапазоне от -5 до 5');
        return false;
    }
}

function validateR(r) {
    if (!r) {
        showMessage('Выберите значение для R');
        return false;
    }

    const parsedR = parseFloat(r);
    if ([1,2,3,4,5].includes(parsedR)) {
        return true;
    } else {
        showMessage('Введите корректное значение для R');
        return false;
    }
}

function showMessage(message, isSuccess = false) {
    log.innerHTML = message;
    log.classList.toggle("success", isSuccess);
    log.style.display = "block";
}

/*function updatePointPosition(res) {
    const point = document.getElementById("point");

    point.setAttribute("cx", 150 + (120 / res.r) * Number(res.x));
    point.setAttribute("cy", 150 - (120 / res.r) * Number(res.y));
    point.setAttribute("visibility", "visible");
}*/