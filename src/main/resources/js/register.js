const renderOptionProvince = (obj) => {
    return `<option value="${obj.province_id}">${obj.province_name}</option>`;
}

const renderOptionDistrict = (obj) => {
    return `<option value="${obj.district_id}">${obj.district_name}</option>`;
}

const renderOptionWard = (obj) => {
    return `<option value="${obj.ward_id}">${obj.ward_name}</option>`;
}

const getAllDistrictsByProvinceId = (provinceId, elem, callback) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/district/' + provinceId
    }).done(data => {
        const districts = data.results;
        elem.empty();
        $.each(districts, (index, item) => {
            const str = renderOptionDistrict(item);
            elem.append(str);
        });
        if (callback) {
            callback(districts);
        }
    }).fail((error) => {
        console.log(error);
    });
}

const getAllWardsByDistrictId = (districtId, elem) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/ward/' + districtId
    }).done((data) => {
        const wards = data.results;

        elem.empty();

        $.each(wards, (index, item) => {
            const str = renderOptionWard(item);
            elem.append(str);
        });
    }).fail((error) => {
        console.log(error);
    });
}

const getAllProvinces = () => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/'
    }).done(data => {
        const provinces = data.results;
        $("#provinceCre").empty().append('<option value="">Chọn tỉnh, thành phố</option>');
        $.each(provinces, (index, item) => {
            const str = renderOptionProvince(item);
            $("#provinceCre").append(str);
            $("#provinceUp").append(str);
        });
    }).fail((error) => {
        console.log(error);
    });
}

$("#provinceCre").on("change", function() {
    const provinceId = $(this).val();
    getAllDistrictsByProvinceId(provinceId, $("#districtCre"), function(data) {
        const districtId = $("#districtCre").val()
        getAllWardsByDistrictId(districtId, $("#wardCre"));
    });
});

$("#districtCre").on("change", function() {
    const districtId = $(this).val();
    getAllWardsByDistrictId(districtId, $("#wardCre"));
});

$("#provinceUp").on("change", function() {
    const provinceId = $(this).val();
    getAllDistrictsByProvinceId(provinceId, $("#districtUp"), function(data) {
        const districtId = $("#districtUp").val()
        getAllWardsByDistrictId(districtId, $("#wardUp"));
    });
});


$("#districtUp").on('change', function () {
    const districtId = $(this).val();
    getAllWardsByDistrictId(districtId, $("#wardUp"));
})

getAllProvinces();

const btnRegister = $("#register");
btnRegister.on('click', () => {
    event.preventDefault();
    btnRegister.attr("disable", true);

    const obj = {
        username: $("#username").val(),
        password: $("#password").val(),
        fullName: $('#fullName').val(),
        phone: $('#phone').val(),
        locationRegion: {
            provinceId: $("#provinceCre").val(),
            provinceName: $("#provinceCre").find("option:selected").text(),
            districtId: $("#districtCre").val(),
            districtName: $("#districtCre").find("option:selected").text(),
            wardId: $("#wardCre").val(),
            wardName: $("#wardCre").find("option:selected").text(),
            address: $('#address').val()
        }
    }

    setTimeout(() => {
        $.ajax({
            headers: {
                'accept': 'application/json',
                'content-type': 'application/json'
            },
            method: 'POST',
            url: 'http://localhost:8080/api/customers',
            dataType: 'json',
            data: JSON.stringify(obj),
        }).done(data => {
            console.log(data)
            swal.fire({
                icon: 'success',
                title: 'Success',
                text: "Đăng ký thành công !!!"
            })
            $("#formCre").trigger("reset")
            $("#districtCre").empty()
            $("#wardCre").empty()
        })
    })

})