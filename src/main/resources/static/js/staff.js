









function redirectToProfile(staffID) {
    $.ajax({
        url: `/api/staffs/profileStaff/${staffID}`, // Replace with your API endpoint
        method: "GET",
        contentType: 'application/json',
        success: function (staff) {
            let html = `<tr role="row" class="odd">
                            <td>${staff.fullName}</td>
                            <td>${staff.phone}</td>
                            <td>${staff.email}</td>
                            <td>${staff.age}</td>
                            <td>${staff.locationRegion.districtName}</td>
                            <td>${staff.locationRegion.provinceName}</td>
                            <td>${staff.locationRegion.wardName}</td>
                            <td>${staff.locationRegion.address}</td>
                            <td>
                                <button onclick="update(${staff.id})" class="btn btn-outline-secondary edit" data-bs-toggle="modal" data-bs-target="#modalUpdate" data-id="${staff.id}">
                                    <i class="fas fa-user-edit"></i>
                                </button>
                            </td>
                        </tr>`;
            document.getElementById("profile-staff").innerHTML = html;
        },
        error: function () {
            console.log("Error fetching staff profile");
        }
    });
}



