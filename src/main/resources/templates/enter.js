function agree(id) {

    var box = document.getElementById(id);
    var dov = document.getElementById(id + "a");
    var ned = document.getElementById(id + "b");

    if(box.checked) {
        dov.disabled = 0;
        ned.disabled = 0;
    } else {
        dov.disabled = 1;
        ned.disabled = 1;
    }
}