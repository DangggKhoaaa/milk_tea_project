
function renderCustomer (){
    $.ajax({
        url : "http://localhost:8080/api/customers",
        method : "GET"
    }).done (data =>{
        console.log(data)
        let str = '';
        data.forEach(customer => {
            str += `<tr>
                                    <td>${customer.id}</td> 
                                    <td> ${customer.fullName} </td>
                                    <td>${customer.username}</td>
                                    <td>${customer.phone} </td>
                                    <td>
                                        <span class="badge badge-secondary">${customer.role}</span>
                                    </td>
                                    <td>
                                        <span class="badge badge-success">Open</span>
                                    </td>
                                   
                                    <td>
                                        <div class="btn-group dropdown">
                                            <a href="javascript: void(0);" class="dropdown-toggle arrow-none btn btn-light btn-sm" data-toggle="dropdown" aria-expanded="false"><i class="mdi mdi-dots-horizontal"></i></a>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <a class="dropdown-item" href="#">
                                                <i class="mdi mdi-pencil mr-2 text-muted font-18 vertical-middle"></i>Edit</a>
                                                <a class="dropdown-item" href="#">
                                                <i class="mdi mdi-check-all mr-2 text-muted font-18 vertical-middle"></i>Close</a>
                                                <a class="dropdown-item" href="#">
                                                <i class="mdi mdi-star mr-2 font-18 text-muted vertical-middle"></i>Mark as Unread</a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>`
        })
        document.getElementById("body").innerHTML = str;
    })
}

renderCustomer();