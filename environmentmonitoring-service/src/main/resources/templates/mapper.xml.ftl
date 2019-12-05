<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}"/>
</#if>
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
</#if>
</#list>
    </resultMap>

    <!-- 新增${entity} -->
    <insert id="insert${entity}" parameterType="${package.Entity}.${entity}">
        INSERT INTO ${table.name} (
        <#list table.fields as field>
        <#if field_index == (table.fields?size-1)>
        ${field.name}
        <#else>
        ${field.name},
        </#if>
        </#list>
        )
        VALUES (
        <#list table.fields as field>
        <#if field_index == (table.fields?size-1)>
        <#noparse>#</#noparse>{${field.propertyName}}
        <#else>
        <#noparse>#</#noparse>{${field.propertyName}},
        </#if>
        </#list>
        )
    </insert>
</#if>

    <!-- 查找${entity} -->
    <select id="get${entity}" parameterType="${package.Entity}.${entity}" resultMap="BaseResultMap">
        SELECT
        <#list table.fields as field>
        <#if field_index == (table.fields?size-1)>
        ${field.name}
        <#else>
        ${field.name},
        </#if>
        </#list>
        FROM ${table.name}
        <where>
            <trim suffixOverrides="AND|OR">
            <#list table.fields as field>
                <#if field.keyFlag>
                <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                    ${field.name} = <#noparse>#</#noparse>{${field.propertyName}}
                </if>
                </#if>
            </#list>
            </trim>
        </where>
    </select>

    <!-- 分页查找${entity} -->
    <select id="list${entity}ByPage" parameterType="${package.Entity}.${entity}" resultMap="BaseResultMap">
        SELECT
        <#list table.fields as field>
        <#if field_index == (table.fields?size-1)>
        ${field.name}
        <#else>
        ${field.name},
        </#if>
        </#list>
        FROM ${table.name}
        <where>
            <trim suffixOverrides="AND|OR">
                <if test="${entity?uncap_first} != null" >
                <#list table.fields as field>
                    <#if !field.keyFlag>
                    <if test="${entity?uncap_first}.${field.propertyName} != null and ${entity?uncap_first}.${field.propertyName} != ''">
                        ${field.name} = <#noparse>#</#noparse>{${entity?uncap_first}.${field.propertyName}}
                    </if>
                    </#if>
                </#list>
                </if>
            </trim>
        </where>
    </select>

    <!-- 修改${entity} -->
    <update id="update${entity}" parameterType="${package.Entity}.${entity}">
        UPDATE ${table.name}
        <set>
            <trim suffixOverrides=",">
                <#list table.fields as field>
                <#if !field.keyFlag>
                <if test="${field.propertyName} != null">
                    ${field.name} = <#noparse>#</#noparse>{${field.propertyName}},
                </if>
                </#if>
                </#list>
            </trim>
        </set>
        <where>
            <trim suffixOverrides="AND|OR">
            <#list table.fields as field>
            <#if field.keyFlag>
            <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                ${field.name} = <#noparse>#</#noparse>{${field.propertyName}}
            </if>
            </#if>
            </#list>
            </trim>
        </where>
    </update>

    <!-- 逻辑删除${entity} -->
    <update id="delete${entity}" parameterType="${package.Entity}.${entity}">
        UPDATE ${table.name}
        <set>
            <trim suffixOverrides=",">
                <if test="updateTime != null">
                    update_time = <#noparse>#</#noparse>{updateTime},
                </if>
                is_deleted = 1
            </trim>
        </set>
        <where>
            <trim suffixOverrides="AND|OR">
            <#list table.fields as field>
            <#if field.keyFlag>
                <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                    ${field.name} = <#noparse>#</#noparse>{${field.propertyName}}
                </if>
            </#if>
            </#list>
            </trim>
        </where>
    </update>

    <!-- 物理删除${entity} -->
    <!--
    <delete id="delete${entity}" parameterType="${package.Entity}.${entity}">
        DELETE FROM ${table.name}
        <where>
            <trim suffixOverrides="AND|OR">
            <#list table.fields as field>
                <#if field.keyFlag>
                <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                    ${field.name} = <#noparse>#</#noparse>{${field.propertyName}}
                </if>
            </#if>
            </#list>
            </trim>
        </where>
    </delete>
    -->
</mapper>
