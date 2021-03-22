window.onload = () => {
    document.getElementById("searchForm").addEventListener("submit", handleSearch);
    const addBookForm = document.getElementById("addBookForm");
    if (addBookForm) addBookForm.addEventListener("submit", handleNewBook);

    const tableBody = document.getElementById("bookTable").getElementsByTagName("tbody")[0];
    const hasFavorites = document.getElementById("favorite-column") !== null;
    fillContent(tableBody, hasFavorites);
}

function handleSearch(event) {
    const searchText = document.getElementById("searchInput").value;
    const searchMode = document.querySelector('input[name="searchMode"]:checked').value;

    $.get(`/books`, {[searchMode]: searchText},
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
                    <td>${datum.authors.join(',')}</td>
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
    const authors = document.getElementById("newBookAuthors").value;
    const genres = document.getElementById("newGenres").value;

    bookForm.append('isbn', isbn);
    bookForm.append('title', title);
    bookForm.append('authors', authors.split(/\s+/));
    bookForm.append('genres', genres.split(/\s+/));

    $.ajax({
        url: "/books", type: 'POST', data: bookForm,
        processData: false,
        contentType: false,
        success: (resp => {
            document.getElementById("bookTable").getElementsByTagName("tbody")[0].innerHTML += `
                <tr>
                    <td>${isbn}</td>
                    <td>${title}</td>
                    <td>${authors}</td>
                    <td>${formatDate(new Date())}</td>
                    <td>${genres}</td>
                    <td><input type="checkbox" onchange="handleDoFavorite(${isbn})"/></td>
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
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}

function fillContent(tableBody, hasFavorites) {
    $.ajax({
        url: "/books",
        type: 'GET',
        success: (resp) => {
            resp.filter(x => x !== null).forEach(datum => {
                const maybeCell = hasFavorites
                    ? `<td><input id="${datum.isbn}-fav" type="checkbox" onChange="handleDoFavorite(${datum.isbn})" 
                        ${datum.isFavorited && 'checked'}/></td>`
                    : "";
                tableBody.innerHTML +=
                    `
                <tr>
                    <td>${datum.isbn}</td>
                    <td>${datum.title}</td>
                    <td>${datum.authors.join(",")}</td>
                    <td>${datum.dateAdded}</td>
                    <td>${datum.genres.join(',')}</td>
                    ${maybeCell}                  
                </tr>
                `;
            })
        }
    })
}

function handleDoFavorite(isbn) {
    let favElem = document.getElementById(isbn + "-fav");

    const ajaxMethod = favElem.checked ? "post" : "delete";
    $.ajax(
        {
            url: `/books/favorites/${isbn}`,
            type: ajaxMethod,
            error: (message) => console.log(message)
        }
    )
}

// wet
function handleFavorites(event) {
    const tableBody = document.getElementById("bookTable").getElementsByTagName("tbody")[0];
    tableBody.innerHTML = '';
    const hasFavorites = document.getElementById("favorite-column") !== null;

    $.ajax({
        url: "/books/favorites",
        type: 'GET',
        success: (resp) => {
            resp.filter(x => x !== null).forEach(datum => {
                const maybeCell = hasFavorites
                    ? `<td><input id="${datum.isbn}-fav" type="checkbox" onChange="handleDoFavorite(${datum.isbn})" 
                        ${datum.isFavorited && 'checked'}/></td>`
                    : "";
                tableBody.innerHTML +=
                    `
                <tr>
                    <td>${datum.isbn}</td>
                    <td>${datum.title}</td>
                    <td>${datum.authors.join(",")}</td>
                    <td>${datum.dateAdded}</td>
                    <td>${datum.genres.join(',')}</td>
                    ${maybeCell}                  
                </tr>
                `;
            })
        }
    })
}