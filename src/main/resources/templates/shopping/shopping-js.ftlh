<script>
    $(document).ready(function () {
        const table = $('#shopping-table').DataTable({
            "filter": false,
            "info": false,
            "paginate": false
        });

        $("#add-shopping-item-btn").on("click", function () {
            addRowInDom(table);
            adjustIdAndName();
            clearLastRow();
            focusLastRow();
        });
    });

    function clearLastRow() {
        const $table = $("#shopping-table");
        $table.find("tbody").find("tr:last-child").first().find("input").each((index, element) => {
            const nameAttr = $(element).attr("name");
            if (nameAttr && !nameAttr.endsWith("userId")) {
                $(element).val("");
            }
        });
    }

    function addRowInDom(table) {
        const row = table.rows(0).data();
        table.row.add(row[0], row[1], row[2], row[3], row[4]).draw(false);
    }

    function adjustIdAndName() {
        $("#shopping-table").find("tbody").find("tr").each((index, element) => {
            $(element).find("input").each((_, input) => {
                const regex = /\[\d+\]/g;
                const nameAttr = $(input).attr("name");
                const replacement = "[" + index + "]";
                if (nameAttr) {
                    $(input).attr("name", nameAttr.replace(regex, replacement));
                }
                const idAttr = $(input).attr("id");
                const regexId = /[0-9]/g
                if (idAttr) {
                    $(input).attr("id", idAttr.replace(regexId, index));
                }
            })
        });
    }

    function focusLastRow() {
        const table = $("#shopping-table");
        const row = table.find("tbody").find("tr:last-child").get(0);
        $(row).find('input').first().focus();
        $(row).find("td:nth-of-type(4)").find("input").val("LOW")
    }
</script>