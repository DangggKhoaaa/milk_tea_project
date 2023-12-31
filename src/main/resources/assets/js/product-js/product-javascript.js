

const body = document.getElementById("body");
const API_PRODUCT = 'http://localhost:8080/api/products';
let categories = [];
let products = [];
let page = 0;
let size = 2;
let totalPage = 0;
let sort = {
    field: "id",
    sortBy: "asc"
}


function uploadFile() {
    const fileInput = document.getElementById("fileInput");
    const allowedTypes = ["image/jpeg", "image/png", "image/gif", "video/mp4", "video/quicktime"];
    const files = fileInput.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const fileType = file.type;

        if (!allowedTypes.includes(fileType)) {
            alert("Chỉ chọn các tệp ảnh (JPEG, PNG, GIF) hoặc video (MP4, QuickTime).");
            fileInput.value = ""; // Xóa tệp không hợp lệ khỏi ô chọn tệp
            return false;
        }

        const reader = new FileReader();

        reader.addEventListener("load", function () {
            const imageURL = reader.result;
            const img = document.createElement("img");
            img.src = imageURL;
            img.style.maxWidth = "100%";
            img.style.maxHeight = "300px"; // Tùy chỉnh kích thước hiển thị ảnh
            const imagePreview = document.getElementById("imagePreview");
            imagePreview.innerHTML = ""; // Xóa nội dung cũ trước khi thêm ảnh mới
            imagePreview.appendChild(img);
        });

        reader.readAsDataURL(file);
    }

    return true;
}


function eventShowForm() {
    $('#modalCreate').modal('show')
}

function createProducts(e) {
e.preventDefault();
    const form = document.getElementById("formCreate");
    const formInput = new FormData(form);
    formInput.append('name', $('#fullNameCre').val())
    formInput.append('price', $('#priceCre').val())
    formInput.append('img', $('#avatarCre')[0].files[0])
    $.ajax({
        url: "http://localhost:8080/api/products",
        method: "POST",
        contentType: false,
        cache: false,
        processData: false,
        data: formInput
    }).done(e => {
        alert('Success');
        renderProducts();
        $('#modalCreate').modal('hide')
        form.reset();
    });
}
function editProducts(e) {
e.preventDefault();
    const formEdit = document.getElementById("modalUp");
    const formEditProduct = new FormData(formEdit);
    formEditProduct.append('name', $('#fullNameUp').val())
    formEditProduct.append('price', $('#priceUp').val())
    formEditProduct.append('img', $('#imgUp')[0].files[0])
    $.ajax({
        url: "http://localhost:8080/api/products" + id,
        method: "PUT",
        contentType: false,
        cache: false,
        processData: false,
        data: formEditProduct
    }).done(e => {
        alert('Success');
        renderProducts();
        $('#modalCreate').modal('hide')
        formEdit.reset();
    });
}

const renderProducts = () => {
    body.innerHTML = '';
    $.ajax({
        url: `http://localhost:8080/api/products?page=${page || 0}&size=${size || 0}`,
        method: 'GET'

    }).done(data => {
        products = data.content;
        totalPage = data.totalPages;
        console.log(products)
        products.forEach((product, index) => {
            body.innerHTML += `
               <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td><img  class="imgProduct" src="${product.imgUrl || ""}" ></td>
                    <td>
                      <button type="button" class="btn btn-primary" onclick="showEdit(${product.id})" data-bs-toggle="modal" data-bs-target="#exampleModal">Edit</button>
                                    <button type="button" class="btn btn-danger" onclick="deleteById(${product.id})">Delete</button>
                    </td>
                    </tr>

`
        })
        renderPagination();
        if(page > 0 && products.length === 0) {
            page = 0;
            renderProducts();
        }
    });

}


const renderPagination = () => {
    const pagination = $('#pagination');
    pagination.empty();
    //render Previous
    console.log(totalPage)
    pagination.append(` <li onclick="onPageChange(${page})"
        class="page-item ${page === 0 ? 'disabled' : ''}">
      <a class="page-link" href="#" tabindex="-1" ${page === 0 ? 'aria-disabled="true"' : ''} >Previous</a>
    </li>`)
    for (let i = 1; i <= totalPage; i++) {
        pagination
            .append(`<li class="page-item" onclick="onPageChange(${i})">
                            <a class="page-link ${page + 1 === i ? 'active' : ''} "
                            ${page + 1 === i ? 'aria-current="page"' : ''} href="#">
                                ${i}
                            </a></li>`);

    }

    pagination.append(` <li onclick="onPageChange(${page + 2})"
        class="page-item ${page === totalPage - 1 ? 'disabled' : ''}">
      <a class="page-link" href="#" tabindex="-1" ${page === totalPage - 1 ? 'aria-disabled="true"' : ''} >Next</a>
    </li>`);
}

renderProducts();
const onPageChange = (pageChange) => {
    console.log(pageChange)

    if (pageChange < 1 || pageChange > totalPage || pageChange === page) {
        return;
    }
    //console.log(page);
    page = pageChange - 1;
    renderProducts();
}

function onSearch() {
    search = document.getElementById('search').value;
    renderProducts();
}

function onSort(str) {
    if (sort.field === str) {
        sort.sortBy = sort.sortBy === 'desc' ? 'asc' : 'desc';
    } else {
        sort.field = str;
        sort.sortBy = 'asc';
    }
    renderProducts();
}

function deleteById(id) {
    $.ajax({
        url: API_PRODUCT + '/' + id,
        method: 'DELETE',
    }).done(e => {
        alert('Success');
        onPageChange(1)
    })
}