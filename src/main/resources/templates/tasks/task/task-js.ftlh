<#import "../../selectors.ftlh" as selectors/>

<script>

    $(document).ready(() => {
        $("#create-task-form").find("input, textarea, select").each((_, input) => {
            $(input).on("input", checkFormInputs);
        })
    })


    function checkFormInputs() {
        const invalidForm = $("#create-task-form").find("input:invalid, textarea:invalid, select:invalid").length > 0;
        $("#save-task-btn").prop("disabled", invalidForm);
    }

    function toggleCheckbox(element) {
        $(element).parent().find('input[type=checkbox]').click();
    }

    async function updateTaskStatus(element, id) {
        const url = "tasks/" + id;
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            location.reload();
        } else {
            let data = await response.json();
            let errorMsg = "Request to update task Status failed with a statuscode " +
                data.status + " - " + data.error;
            handleTaskUpdateError(errorMsg);
        }
    }


    function toggleTaskState(element, id) {
        const selector = $(element).parentsUntil("[${selectors.taskSelector}]");
        if ($(selector).find(".task-status").hasClass("done")) {
            $(selector).find(".task-status").removeClass("done");
            $(selector).find(".task-status").addClass("todo");
            $(selector).find("label").css("text-decoration", "none")
        } else if ($(selector).find(".task-status").hasClass("todo")) {
            $(selector).find(".task-status").removeClass("todo");
            $(selector).find(".task-status").addClass("done");
            $(selector).find("label").css("text-decoration", "line-through")
        }
        updateTaskStatus(element, id);
    }


    function editTask(id, title, description, priority, category, date) {
        if (id) {
            $("#taskModalLabel").text("Edit Task");
        } else {
            $("#taskModalLabel").text("Create new Task");
        }

        $('#taskModal').modal('toggle');
        $("#task-id").val(id);
        $("#task-title").val(title);
        $("#task-description").val(description);
        $("#task-priority").val(priority);
        $("#task-category").val(category);
        $("#task-date").val(date);
    }


    async function saveTask() {
        $('#taskModal').modal('toggle');
        const id = Number($("#task-id").val());
        const title = $("#task-title").val();
        const description = $("#task-description").val();
        let priority = $("#task-priority").val();
        !priority ? priority = "LOW" : "";
        let category = $("#task-category").val();
        !category ? category = "PERSONAL" : "";
        const done = false;
        const startingDate = $("#task-date").val();
        const userId = $("#task-userId").val();

        let taskDto;
        taskDto = {title, description, priority, category, done, startingDate}
        id ? taskDto.id = id : "";
        userId ? taskDto.userId = userId : "";

        const url = "/tasks/" + id;
        const response = await fetch(url, {
            body: JSON.stringify(taskDto),
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            location.reload();
        } else {
            let data = await response.json();
            console.log(data)
            //console.log(data.message); <- actual reason why request fails -> maybe in future display somewhere idk
            let errorMsg = "Request to save task failed with a statuscode " +
                data.status + " - " + data.message;
            handleTaskUpdateError(errorMsg);
        }
    }


    function handleTaskUpdateError(msg) {
        $("#fail-toast").text(msg);
        $("#fail-toast").show();
        setTimeout(() => {
            $("#fail-toast").hide();
        }, 7000);
    }

    async function deleteTask(id) {
        const url = "/tasks/" + id;
        const response = await fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            location.reload();
        } else {
            let data = await response.json();
            //console.log(data.message); <- actual reason why request fails -> maybe in future display somewhere idk
            let errorMsg = "Request to delete task failed with a statuscode " +
                data.status + " - " + data.error;
            handleTaskUpdateError(errorMsg);
        }
    }

</script>
