const API_BILLS = 'http://localhost:8080/api/bills'

function createBill(id){
    $.ajax({
        url : API_BILLS,
        method : "POST"
    })
}