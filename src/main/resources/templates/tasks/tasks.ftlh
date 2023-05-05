<#-- @ftlvariable name="tasks" type="com.titzko.freemarkertodo.model.Task[]" -->
<#-- @ftlvariable name="date" type="java.util.Date" -->


<#import "../pageframe/pageframe.ftlh" as pageframe/>
<#import "../selectors.ftlh" as selectors/>
<#import "/spring.ftl" as spring />



<@pageframe.pageframe>
    <div id="sidebar">
        <#include "../sidebar/sidebar.ftlh">
    </div>
    <div id="body" ${selectors.taskPage}>
        <div class="py-5">
            <div id="fail-toast" class="alert alert-danger" role="alert" style="display: none">
                This is a danger alert—check it out!
            </div>
            <div class="mt-1 d-flex justify-content-center">
                <div class="h2">${(date?datetime?string('dd-MM-yyyy'))!"All"}</div>
                </div>
            </div>
            <div class="w-100 d-flex justify-content-center mt-5 mb-5">
                <button class="btn btn-secondary theme-button btn-lg btn-block"
                        onclick="editTask('','','','','','','')">
                    +
                </button>
            </div>
        <div ${selectors.tasksWrapper} class="task-wrapper">
            <#list (tasks.tasks)![] as task>
                <#include "task/task.ftl">
            </#list>
        </div>
    </div>
    <#include "./task/task-js.ftlh">
    <#include "./task/task-css.ftlh">
    <#include "./task/modal.ftlh">
    <#include "./tasks-css.ftlh">
    </div>
</@pageframe.pageframe>