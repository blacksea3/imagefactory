package com.bla.imagefetch.common.dal.imagefactory.auto.mapper;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table task_detail.
 * 原子任务表
 */
public interface TaskDetailDOMapper{

    /**
     * desc:不带Id插入表:task_detail.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO task_detail( ID ,SCRIPT ,CONTENT ,EXT_INFO ,SERVICE_NAME ,INSTANCE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( null , #{script,jdbcType=VARCHAR} , #{content,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , #{instanceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithoutID(TaskDetailDO entity);
    /**
     * desc:带Id插入表:task_detail.<br/>
     * descSql =  <![CDATA[ INSERT INTO task_detail( ID ,SCRIPT ,CONTENT ,EXT_INFO ,SERVICE_NAME ,INSTANCE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( #{id,jdbcType=VARCHAR} , #{script,jdbcType=VARCHAR} , #{content,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , #{instanceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithID(TaskDetailDO entity);
    /**
     * desc:不带Id批量插入表:task_detail.<br/>
     * descSql =  INSERT INTO task_detail( ID ,SCRIPT ,CONTENT ,EXT_INFO ,SERVICE_NAME ,INSTANCE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES ( null , #{item.script,jdbcType=VARCHAR} , #{item.content,jdbcType=VARCHAR} , #{item.extInfo,jdbcType=VARCHAR} , #{item.serviceName,jdbcType=VARCHAR} , #{item.instanceName,jdbcType=VARCHAR} , now() , now() ) 
     * @param list list
     * @return Long
     */
    Long insertBatchWithoutID(List<TaskDetailDO> list);
    /**
     * desc:根据Id更新表:task_detail.<br/>
     * descSql =  <![CDATA[ UPDATE task_detail SET GMT_MODIFIED = now() ,SCRIPT = #{script,jdbcType=VARCHAR} ,CONTENT = #{content,jdbcType=VARCHAR} ,EXT_INFO = #{extInfo,jdbcType=VARCHAR} ,SERVICE_NAME = #{serviceName,jdbcType=VARCHAR} ,INSTANCE_NAME = #{instanceName,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param entity entity
     * @return Long
     */
    Long update(TaskDetailDO entity);
    /**
     * desc:根据Id删除数据:task_detail.<br/>
     * descSql =  <![CDATA[ DELETE FROM task_detail WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return Long
     */
    Long deleteById(Integer id);
    /**
     * desc:根据Id获取数据:task_detail.<br/>
     * descSql =  SELECT * FROM task_detail WHERE <![CDATA[ ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return TaskDetailDO
     */
    TaskDetailDO queryById(Integer id);
    /**
     * desc:根据普通索引InstanceName获取数据:task_detail.<br/>
     * descSql =  SELECT * FROM task_detail WHERE <![CDATA[ INSTANCE_NAME = #{instanceName,jdbcType=VARCHAR} ]]>
     * @param instanceName instanceName
     * @return List<TaskDetailDO>
     */
    List<TaskDetailDO> queryByInstanceName(@Param("instanceName")String instanceName);
}
