<#import "/spring.ftl" as spring />

<#macro inputText  model="" label=""  type="text" attributes...>
    <div class="d-flex flex-column">
        <label class="base-label">${label}</label>
        <#if attributes??>
            <#local attrs = joinAttributes(attributes)>
        </#if>
        <#if model?has_content>
            <div class="base-input">
                <@spring.formInput path=model fieldType=type attributes=attrs!""/>
            </div>
        <#else>
            <div class="base-input">
                <input type="${type}">
            </div>
        </#if>
    </div>
</#macro>


<#function joinAttributes attributes...>
    <#local result = "">
    <#if attributes?is_sequence>
        <#list attributes as attr>
            <#list attr as k,v>
                <#local result +=  k + "='" + v + "'">
            </#list>
        </#list>
    </#if>
    <#return result>
</#function>


