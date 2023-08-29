

const updateProfile = $('#updateProfile');
    updateProfile.on('click', function () {
    const id = $(this).data('id');
    // const id = 2;
    console.log(id)
    $.ajax({
        headers: {
            'accept': 'application/json',
            'content-type': 'application/json'
        },
        method: "GET",
        url: "http://localhost:8080/api/customers" + '/' + id,
    }).done(data => {
        if (Object.keys(data).length > 0) {
            let customer = data;
            let locationRegion = customer.locationRegion;
            getAllDistrictsByProvinceId(locationRegion.provinceId, $("#districtUp"), data).then((data) => {
                $('#districtUp').val(locationRegion.districtId);

                getAllWardsByDistrictId(locationRegion.districtId, $("#wardUp")).then((data) => {
                    $('#wardUp').val(locationRegion.wardId);
                })
            })

            $('#idUp').val(customer.id);
            $('#fullNameUp').val(customer.fullName);
            $('#phoneUp').val(customer.phone);

            $('#provinceUp').val(locationRegion.provinceId);

            $('#addressUp').val(locationRegion.address);

            $('#modalUpdate').show();
        }
    }).fail((error) => {
        console.log(error);
    })
})

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
        $("#provinceUp").empty().append('<option value=""></option>');
        $.each(provinces, (index, item) => {
            const str = renderOptionProvince(item);
            $("#provinceUp").append(str);
        });
    }).fail((error) => {
        console.log(error);
    });
}

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