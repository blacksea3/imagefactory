package com.bla.imagefetch.common.dal.imagefactory.auto.mapper;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table task_config.
 * 任务配置表
 */
public interface TaskConfigDOMapper{

    /**
     * desc:不带Id插入表:task_config.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO task_config( ID ,NAME ,DESCRIPTION ,STATUS ,EXT_INFO ,SERVICE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( null , #{name,jdbcType=VARCHAR} , #{description,jdbcType=VARCHAR} , #{status,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Integer
     */
    Integer insertWithoutID(TaskConfigDO entity);
    /**
     * desc:带Id插入表:task_config.<br/>
     * descSql =  <![CDATA[ INSERT INTO task_config( ID ,NAME ,DESCRIPTION ,STATUS ,EXT_INFO ,SERVICE_NAME ,GMT_CREATE ,GMT_MODIFIED )VALUES( #{id,jdbcType=INTEGER} , #{name,jdbcType=VARCHAR} , #{description,jdbcType=VARCHAR} , #{status,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , #{serviceName,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Integer
     */
    Integer insertWithID(TaskConfigDO entity);
    /**
     * desc:根据Id全量更新表:task_config.<br/>
     * descSql =  UPDATE task_config SET GMT_MODIFIED = now() ,NAME = #{name,jdbcType=VARCHAR} ,DESCRIPTION = #{description,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=VARCHAR} ,EXT_INFO = #{extInfo,jdbcType=VARCHAR} ,SERVICE_NAME = #{serviceName,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Integer
     */
    Integer updateAll(TaskConfigDO entity);
    /**
     * desc:根据Id删除数据:task_config.<br/>
     * descSql =  <![CDATA[ DELETE FROM task_config WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return Integer
     */
    Integer deleteById(Integer id);
    /**
     * desc:根据Id获取数据:task_config.<br/>
     * descSql =  SELECT * FROM task_config WHERE <![CDATA[ ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return TaskConfigDO
     */
    TaskConfigDO queryById(Integer id);
    /**
     * desc:根据普通索引Name获取数据:task_config.<br/>
     * descSql =  SELECT * FROM task_config WHERE <![CDATA[ NAME = #{name,jdbcType=VARCHAR} ]]>
     * @param name name
     * @return TaskConfigDO
     */
    TaskConfigDO queryByName(@Param("name")String name);
}
