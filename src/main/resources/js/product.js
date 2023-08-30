
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
                            <p></p>
                            <h4 class="text-primary">${product.price}VND</h4>
                        </div>
                        <div class="store-overlay" >
                            <a href="" class="btn btn-primary rounded-pill py-2 px-4 m-2" onclick="showProductDetail(${product.id})" data-bs-toggle="modal"  data-bs-target="#modalCreate">
                                More Detail<i class="fa fa-arrow-right ms-2" >
                            </i>
                            </a>
                            <p class="btn btn-dark rounded-pill py-2 px-4 m-2" onclick="addToCart(${product.id})">Add to Cart <i class="fa fa-cart-plus ms-2"></i></p>
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
        document.getElementById("sizeS").onclick = function (){
            updateProductPrice(data.price);
        }
        document.getElementById("sizeM").onclick = function (){
            updateProductPrice(data.price);
        }
        document.getElementById("sizeL").onclick = function (){
            updateProductPrice(data.price);
        }
        document.getElementById("quantity").onclick = function (){
            updateProductPrice(data.price);
        }

        updateProductPrice(data.price);
    })
    function updateProductPrice(basePrice) {
        let selectedSize = document.querySelector('input[name="size"]:checked').id;
        const quantity = parseInt(document.querySelector('.quantity').value);
        const priceElement = document.getElementById("productPrice")
        const sizeS = basePrice;
        const sizeM = basePrice + 5000;
        const sizeL = basePrice + 10000;

        if (selectedSize === "sizeS") {
            priceElement.textContent = sizeS * quantity;
        }
        if (selectedSize === "sizeM") {
            priceElement.textContent = sizeM * quantity;
        }
        if (selectedSize === "sizeL") {
            priceElement.textContent = sizeL * quantity;
        }
    }
}

const cart = [];

function addToCart(productId) {
    const product = products.find(item => item.id === productId);

    if (product) {
        const productCopy = { ...product };

        const existingProduct = cart.find(item => item.id === product.id);

        if (existingProduct) {
            existingProduct.quantity++;
        } else {
            productCopy.quantity = 1;
            cart.push(productCopy);
        }

        const countElement = document.getElementById('count');
        if (countElement) {
            const currentCount = parseInt(countElement.innerText);
            countElement.innerText = currentCount + 1;
        }
        console.log(cart);
    }
}

const cartItemsContainer = document.getElementById('cartItems');
cart.forEach((product) => {
    console.log(product)
    const productHtml = `
    <div class="d-flex align-items-center mb-3">
      <img src="${product.imgUrl}" alt="${product.name}" class="me-3" style="max-width: 50px;">
      <div>
        <h6 class="mb-0">${product.name}</h6>
        <p class="mb-0">Price: €${product.price}</p>
        <p class="mb-0">Quantity: ${product.quantity}</p>
      </div>
    </div>
  `;
    cartItemsContainer.innerHTML += productHtml;
});


const cartSummaryContainer = document.getElementById('cartSummary');


let totalItems = 0;
let totalPrice = 0;

cart.forEach((product) => {
    totalItems += product.quantity;
    totalPrice += product.price * product.quantity;
});

const cartSummaryHtml = `
  <div>
    <p>Total items: ${totalItems}</p>
    <p>Total price: €${totalPrice}</p>
  </div>
`;
cartSummaryContainer.innerHTML = cartSummaryHtml;