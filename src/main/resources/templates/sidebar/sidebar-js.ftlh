<script>
    function filterDate(date, category) {
        let url = "/tasks/date/" + date;
        if (category) {
            url = url + "/" + category;
        }
        window.location.href = url;
    }

    function filterCategory(category, date) {
        let url;
        if(date){
             url = "/tasks/date/" + date + "/" + category;
        } else {
             url = "/tasks/category/" + category
        }
        window.location.href = url;
    }
</script>