let allLogs = [];

// Load statistics when page opens
loadStatistics();
loadLogs();

async function loadStatistics() {

    try {

        const response = await fetch("/api/logs/stats");

        const stats = await response.json();

        const container = document.getElementById("statsContainer");

        container.innerHTML = "";

        for (const key in stats) {

            container.innerHTML += `
                <div class="col-md-3 mb-3">
                    <div class="card text-center shadow">
                        <div class="card-body">
                            <h5>${key}</h5>
                            <h2>${stats[key]}</h2>
                        </div>
                    </div>
                </div>
            `;
        }
    }catch(error){
        console.log(error);
    }
}

document.getElementById("uploadBtn").addEventListener("click", uploadFile);
document.getElementById("searchBox").addEventListener("keyup", applyFilters);
document.getElementById("levelFilter").addEventListener("change", applyFilters);
document.getElementById("clearBtn").addEventListener("click", clearLogs);

async function uploadFile() {

    const fileInput = document.getElementById("logFile");

    if (fileInput.files.length === 0) {
        alert("Please select a log file.");
        return;
    }

    const formData = new FormData();
    formData.append("file", fileInput.files[0]);

    try {

        const response = await fetch("/api/logs/upload", {
            method: "POST",
            body: formData
        });

        const message = await response.text();

        document.getElementById("uploadMessage").innerHTML =
            "<div class='alert alert-success'>" + message + "</div>";

        // Refresh statistics after upload
        loadStatistics();
        loadLogs();

    } catch (error) {

        console.error(error);

        document.getElementById("uploadMessage").innerHTML =
            "<div class='alert alert-danger'>Upload failed!</div>";
    }
}

async function loadLogs() {

    try {

        const response = await fetch("/api/logs");

        const logs = await response.json();
        allLogs = logs;
        populateLevelFilter();

        const table = document.getElementById("logTable");

        table.innerHTML = "";

        displayLogs(allLogs);
        applyFilters();

    }
    catch(error){

        console.log(error);

    }

}

function displayLogs(logs) {

    const table = document.getElementById("logTable");

    table.innerHTML = "";

    logs.forEach((log, index) => {

        table.innerHTML += `
            <tr>
                <td>${index + 1}</td>
                <td>${log.timestamp}</td>
                <td>${log.level}</td>
                <td>${log.message}</td>
            </tr>
        `;

    });

}

function applyFilters() {

    const keyword = document
        .getElementById("searchBox")
        .value
        .toLowerCase();

    const selectedLevel =
        document.getElementById("levelFilter").value;

    const filteredLogs = allLogs.filter(log => {

        const matchesSearch =

            log.timestamp.toLowerCase().includes(keyword) ||

            log.level.toLowerCase().includes(keyword) ||

            log.message.toLowerCase().includes(keyword);

        const matchesLevel =

            selectedLevel === "" ||

            log.level === selectedLevel;

        return matchesSearch && matchesLevel;

    });

    displayLogs(filteredLogs);

}

function populateLevelFilter() {

    const filter = document.getElementById("levelFilter");

    filter.innerHTML = '<option value="">All Levels</option>';

    const uniqueLevels = [...new Set(allLogs.map(log => log.level))];

    uniqueLevels.forEach(level => {

        filter.innerHTML +=
            `<option value="${level}">${level}</option>`;

    });

}

async function clearLogs() {

    if (!confirm("Are you sure you want to delete all logs?")) {
        return;
    }

    try {

        const response = await fetch("/api/logs/clear", {
            method: "DELETE"
        });

        const message = await response.text();

        document.getElementById("uploadMessage").innerHTML =
            `<div class="alert alert-success">${message}</div>`;

        loadLogs();
        loadStatistics();

    } catch (error) {

        console.log(error);

    }

}