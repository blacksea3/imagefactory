package com.bla.imagefetch.common.dal.imagefactory.auto.mapper;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table task_instance.
 * 任务实例表
 */
public interface TaskInstanceDOMapper{

    /**
     * desc:不带Id插入表:task_instance.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO task_instance( ID ,NAME ,DESCRIPTION ,CONFIG_NAME ,STATUS ,PRIORITY ,TOTAL_NUM ,HANDLE_NUM ,SERVICE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( null , #{name,jdbcType=VARCHAR} , #{description,jdbcType=VARCHAR} , #{configName,jdbcType=VARCHAR} , #{status,jdbcType=VARCHAR} , #{priority,jdbcType=VARCHAR} , #{totalNum,jdbcType=VARCHAR} , #{handleNum,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithoutID(TaskInstanceDO entity);
    /**
     * desc:带Id插入表:task_instance.<br/>
     * descSql =  <![CDATA[ INSERT INTO task_instance( ID ,NAME ,DESCRIPTION ,CONFIG_NAME ,STATUS ,PRIORITY ,TOTAL_NUM ,HANDLE_NUM ,SERVICE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( #{id,jdbcType=INTEGER} , #{name,jdbcType=VARCHAR} , #{description,jdbcType=VARCHAR} , #{configName,jdbcType=VARCHAR} , #{status,jdbcType=VARCHAR} , #{priority,jdbcType=VARCHAR} , #{totalNum,jdbcType=VARCHAR} , #{handleNum,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithID(TaskInstanceDO entity);
    /**
     * desc:根据Id全量更新表:task_instance.<br/>
     * descSql =  UPDATE task_instance SET GMT_MODIFIED = now() ,NAME = #{name,jdbcType=VARCHAR} ,DESCRIPTION = #{description,jdbcType=VARCHAR} ,CONFIG_NAME = #{configName,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=VARCHAR} ,PRIORITY = #{priority,jdbcType=VARCHAR} ,TOTAL_NUM = #{totalNum,jdbcType=VARCHAR} ,HANDLE_NUM = #{handleNum,jdbcType=VARCHAR} ,SERVICE_NAME = #{serviceName,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long updateAll(TaskInstanceDO entity);
    /**
     * desc:根据Id删除数据:task_instance.<br/>
     * descSql =  <![CDATA[ DELETE FROM task_instance WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return Long
     */
    Long deleteById(Integer id);
    /**
     * desc:根据Id获取数据:task_instance.<br/>
     * descSql =  SELECT * FROM task_instance WHERE <![CDATA[ ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return TaskInstanceDO
     */
    TaskInstanceDO queryById(Integer id);
    /**
     * desc:根据普通索引Name获取数据:task_instance.<br/>
     * descSql =  SELECT * FROM task_instance WHERE <![CDATA[ NAME = #{name,jdbcType=VARCHAR} ]]>
     * @param name name
     * @return TaskInstanceDO
     */
    TaskInstanceDO queryByName(@Param("name")String name);
}
