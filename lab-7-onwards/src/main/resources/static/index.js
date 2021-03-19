window.onload = () => {
    document.getElementById("searchForm").addEventListener("submit", handleSearch);
    document.getElementById("addBookForm").addEventListener("submit", handleNewBook);

    let tableBody = document.getElementById("bookTable").getElementsByTagName("tbody")[0];
    fillContent(tableBody);
}

function handleSearch(event) {
    const searchText = document.getElementById("searchInput").value;
    const searchMode = document.querySelector('input[name="searchMode"]:checked').value;
    $.get(`/search/${searchMode}`, {[searchMode]: searchText},
        (resp) => {
            if (resp === null) return;
            const tableBody = document.getElementById("bookTable").getElementsByTagName("tbody")[0];
            tableBody.innerHTML = '';
            const data = Array.isArray(resp) ? resp : new Array(resp);
            data.forEach(datum => {
                tableBody.innerHTML += `
                <tr>
                    <td>${datum.isbn}</td>
                    <td>${datum.title}</td>
                    <td>${datum.author}</td>
                    <td>${datum.dateAdded}</td>
                    <td>${datum.genres.join(',')}</td>

                </tr>
                `;
            })
        }
    )
    event.preventDefault();
}

function handleNewBook(event) {
    let bookForm = new FormData();
    const isbn = document.getElementById("newBookIsbn").value;
    const title = document.getElementById("newBookTitle").value;
    const author = document.getElementById("newBookAuthor").value;

    bookForm.append('isbn', isbn);
    bookForm.append('title', isbn);
    bookForm.append('author', isbn);

    $.ajax({
        url: "/book", type: 'POST', data: bookForm,
        processData: false,
        contentType: false,
        success: (resp => {
            document.getElementById("bookTable").getElementsByTagName("tbody")[0].innerHTML += `
                <tr>
                    <td>${isbn}</td>
                    <td>${title}</td>
                    <td>${author}</td>
                    <td>${formatDate(new Date())}</td>
                </tr>
            `;
        }),
        error: (message) => {
            alert("Error occurred!" + message);
        }
    });
    event.preventDefault();
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}

function fillContent(tableBody) {
    $.ajax({
        url: "/books",
        type: 'GET',
        success: (resp) => {
            resp.forEach(datum => {
                tableBody.innerHTML +=
                    `
                <tr>
                    <td>${datum.isbn}</td>
                    <td>${datum.title}</td>
                    <td>${datum.author}</td>
                    <td>${datum.dateAdded}</td>
                </tr>
                `;
            })
        }
    })
}