package com.bla.imagefetch.base.model2.auto.mapper;

import com.bla.imagefetch.base.model2.auto.dataobject.TaskDetailDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table task_detail.
 * 原子任务表
 */
public interface TaskDetailDOMapper{

    /**
     * desc:插入表:task_detail.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO task_detail( ID ,SCRIPT ,CONTENT ,EXT_INFO ,SERVICE_NAME ,INSTANCE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( null , #{script,jdbcType=VARCHAR} , #{content,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , #{instanceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insert(TaskDetailDO entity);
    /**
     * desc:批量插入表:task_detail.<br/>
     * descSql =  INSERT INTO task_detail( ID ,SCRIPT ,CONTENT ,EXT_INFO ,SERVICE_NAME ,INSTANCE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES ( null , #{item.script,jdbcType=VARCHAR} , #{item.content,jdbcType=VARCHAR} , #{item.extInfo,jdbcType=VARCHAR} , #{item.serviceName,jdbcType=VARCHAR} , #{item.instanceName,jdbcType=VARCHAR} , now() , now() ) 
     * @param list list
     * @return Long
     */
    Long insertBatch(List<TaskDetailDO> list);
    /**
     * desc:根据主键删除数据:task_detail.<br/>
     * descSql =  <![CDATA[ DELETE FROM task_detail WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return Long
     */
    Long deleteById(Integer id);
    /**
     * desc:根据主键获取数据:task_detail.<br/>
     * descSql =  SELECT * FROM task_detail WHERE <![CDATA[ ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return TaskDetailDO
     */
    TaskDetailDO getById(Integer id);
    /**
     * desc:根据普通索引InstanceName获取数据:task_detail.<br/>
     * descSql =  SELECT * FROM task_detail WHERE <![CDATA[ INSTANCE_NAME = #{instanceName,jdbcType=VARCHAR} ]]>
     * @param instanceName instanceName
     * @return List<TaskDetailDO>
     */
    List<TaskDetailDO> queryByInstanceName(@Param("instanceName")String instanceName);
}
