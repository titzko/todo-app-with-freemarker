<#-- @ftlvariable name="task" type="com.titzko.freemarkertodo.model.Task" -->

<#import "../../selectors.ftlh" as selectors/>
<#import "/spring.ftl" as spring />


<#if task.isDone()>
    <#assign status = "done">
<#else>
    <#assign status = "todo">
</#if>


<#--<form>
${task.title}
    <#assign model="tasks.tasks[${task_index}].title">
    <@spring.formInput path=model/>
    ${model}
</form>-->


<div class="task d-flex justify-content-between" ${selectors.taskSelector}>
    <div class="d-flex justify-content-start">
        <div class="task-left pl-2 pl-md-5 pr-md-5 py-4 ">
            <div class="task-status ${status}"></div>
        </div>
        <div>
            <div class="task-checkbox d-flex flex-column px-3">
                <div class="d-flex align-items-center pt-2">
                    <#assign model="tasks.tasks[${task_index}].done">
                    <@spring.formCheckbox  path=model attributes="onclick='toggleTaskState(this,${task.id})'"/>
                    <label class="task-checkbox-label"
                            <#if task.isDone()>
                                style="text-decoration: line-through"
                            </#if>
                           onclick="toggleCheckbox(this, ${task.id})">
                        ${task.title} <#if task.startingDate??>-</#if> ${(task.startingDate?date)!""}
                    </label>
                </div>
                <div>
                    ${task.description}
                </div>
            </div>
        </div>
    </div>
    <div class="d-flex flex-column justify-content-between mb-2">
        <div class="d-flex justify-content-end mt-2 fa-2x">
            <button class="header-button" style="margin: 0; padding: 0;"
                    onclick='editTask("${task.id!""}",
                            "${task.title!""}",
                            "${task.description!""}",
                            "${task.priority!""}",
                            "${task.category!""}",
                            "${(task.startingDate?string["yyyy-MM-dd"])!""}",
                            "${task.userId!""}",
                            )'>
                <i class="bi bi-pencil-square mx-1" style="font-size: 22px"></i>
            </button>
            <button class="header-button" style="margin: 0; padding: 0;"
                    onclick="deleteTask(${task.id})">
                <i class="bi bi-trash mx-3" style="font-size: 22px"></i>
            </button>
        </div>
        <div class="mr-3 d-flex justify-content-end pt-4 ${task.priority}">${task.priority}</div>
    </div>
</div>

