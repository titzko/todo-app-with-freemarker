<#include "./sidebar-css.ftlh">
<#include "./sidebar-js.ftlh">

<div>
    <#assign sum = 0>

    <#list categories?values as value>
        <#assign sum = sum + value>
    </#list>

    <a href="/tasks">
        <div class="category <#if !category?? && !date??>category-active</#if>">
            All - ${sum}
        </div>
    </a>

    <div class="seperator mb-5"></div>

    <#list categories as key, value>
            <div class="category <#if key == category!"category-1">category-active</#if>"
                 onclick="filterCategory('${key!""}', '${(date?date?string('yyyy-MM-dd'))!""}')">
                ${key} - ${value!""}
            </div>
    </#list>

    <div class="seperator my-5"></div>

    <div class="date-wrapper">
        <#list taskDates as key, value>
            <#assign active = key?date?string == (date?date?string)!"">
            <div class="category <#if active>category-active</#if>"
                 onclick="filterDate('${key?date?string('yyyy-MM-dd')}', '${category!""}')">
                ${(key?datetime?string('dd-MM-yyyy'))!""} - ${(value.amount)!""}
                <#if active>
                    <ul>
                        <#list value.tasks as task>
                            <li>${task.title!""}</li>
                        </#list>
                    </ul>
                </#if>
            </div>
        </#list>
    </div>
</div>