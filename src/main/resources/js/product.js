
const API_PRODUCT  = 'http://localhost:8080/api/products';

function renderProductHome(){
    $.ajax({
        url : API_PRODUCT,
        method : "GET"
    }).done(data => {
        products = data.content;
        console.log(products)
        let str = '';
        products.forEach(product => {

            str += `<div class="col-lg-4 col-md-6 wow fadeInUp"  data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: fadeInUp;">
                    <div class="store-item position-relative text-center">
                        <img class="img-fluid" src="${product.imgUrl}" alt="">
                        <div class="p-4">
                            <div class="text-center mb-3">
                                <small class="fa fa-star text-primary"></small>
                                <small class="fa fa-star text-primary"></small>
                                <small class="fa fa-star text-primary"></small>
                                <small class="fa fa-star text-primary"></small>
                                <small class="fa fa-star text-primary"></small>
                            </div>
                            <h4 class="mb-3" id="p1">${product.name}</h4>
                            <p>Aliqu diam amet diam et eos. Clita erat ipsum lorem erat ipsum lorem sit sed</p>
                            <h4 class="text-primary">${product.price}VND</h4>
                        </div>
                        <div class="store-overlay" >
                            <a href="" class="btn btn-primary rounded-pill py-2 px-4 m-2" onclick="showProductDetail(${product.id})" data-bs-toggle="modal"  data-bs-target="#modalCreate">
                                More Detail<i class="fa fa-arrow-right ms-2" >
                            </i>
                            </a>
                            <a href="" class="btn btn-dark rounded-pill py-2 px-4 m-2">Add to Cart <i class="fa fa-cart-plus ms-2"></i></a>
                        </div>
                    </div>
                </div>`
        })
        document.getElementById("bodyProduct").innerHTML = str;
    })
}

renderProductHome();

function showProductDetail(id){
    $.ajax({
        url: API_PRODUCT + "/" + id,
        method: "GET"
    }).done(data =>{
        document.getElementById("productName").textContent = data.name;
        document.getElementById("productImg").src = data.imgUrl;
        updateProductPrice(data.price);
    })
    function updateProductPrice(basePrice) {
        let selectedSize = document.querySelector('input[name="size"]:checked').id;
        const priceElement = document.getElementById("productPrice");

        if (selectedSize === "sizeS") {
            basePrice = 39000;
            priceElement.textContent = basePrice;
        }
        if (selectedSize === "sizeM") {
            priceElement.textContent = 44000;
        }
        if (selectedSize === "sizeL") {
            priceElement.textContent = 49000;
        }
    }

    // Gán sự kiện "click" vào phần tử "sizeM"
    document.getElementById("sizeS").addEventListener("click", function() {
        updateProductPrice(parseFloat(document.getElementById("productPrice").textContent));
    });
    document.getElementById("sizeM").addEventListener("click", function() {
        updateProductPrice(parseFloat(document.getElementById("productPrice").textContent));
    });
    document.getElementById("sizeL").addEventListener("click", function() {
        updateProductPrice(parseFloat(document.getElementById("productPrice").textContent));
    });
}