<#import "../selectors.ftlh" as selectors/>


<script defer>
    $(document).ready(function () {
        window.addEventListener("resize", function () {
            if (window.innerWidth > 700) {
                $("#body").show();
                $("#sidebar").show();
            } else {
                $("#sidebar").hide();
            }


            if($("[${selectors.taskPage}]").length) {
                if (window.innerWidth > 700) {
                    $("body").css("display", "grid");
                } else {
                    $("body").css("display", "flex");
                }
            } else {
                $("body").css("display", "block");
            }
        });

        if($("[${selectors.taskPage}]").length) {
            if (window.innerWidth > 700) {
                $("body").css("display", "grid");
            } else {
                $("body").css("display", "flex");
            }
        } else {
            $("body").css("display", "block");
        }
    });

    function toggleSidebar() {
        const $sidebar = $("#sidebar");
        const $body = $("#body");
        if ($sidebar.is(":visible")) {
            $sidebar.hide();
            $body.show();
        } else {
            $body.hide();
            $sidebar.show();
        }
    }

    //https://icons.getbootstrap.com/

</script>