<#-- @ftlvariable name="categories" type="com.titzko.freemarkertodo.model.Category[]" -->
<#-- @ftlvariable name="priorities" type="com.titzko.freemarkertodo.model.Priority[]" -->
<#-- @ftlvariable name="userId" type="Long" -->



<div class="modal fade" id="taskModal" tabindex="-1" role="dialog" aria-labelledby="taskModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="taskModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" class="text-white">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <input id="task-id" class="col" type="hidden">
                </div>
                <div class="row py-3 mr-4">
                    <label class="col-4" for="task-title">Title</label>
                    <input id="task-title" class="col" type="text">
                </div>
                <div class="row py-3 mr-4">
                    <label class=" col-4" for="task-description">Description</label>
                    <textarea rows="4" id="task-description" class="col"></textarea>
                </div>
                <div class="row py-3 mr-4">
                    <label class="col-4" for="task-priority">Priority</label>
                    <select class="col" class="dropdown" name="dropdown" id="task-priority">
                        <#list priorities as prio>
                        <option value="${prio}" ${(prio == 'LOW')?then('selected','')}>${prio}</option>
                        </#list>
                    </select>
                </div>
                <div class="row py-3 mr-4">
                    <label class="col-4" for="task-category">Category</label>
                    <select class="col" class="dropdown" name="dropdown" id="task-category" >
                        <#list categories as key, value>
                            <option value="${key}">${key}</option>
                        </#list>
                    </select>
                </div>
                <div class="row py-3 mr-4">
                    <label class="col-4" for="task-date">Starting Date</label>
                    <input id="task-date" class="col" type="date">
                </div>
                <div class="row py-3 mr-4">
                    <input id="task-userId" class="col" type="hidden" value="${userId!""}">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary theme-button btn-lg btn-block m-auto" onclick="saveTask()">Save changes</button>
            </div>
        </div>
    </div>
    <#include "./modal-css.ftlh">
</div>
