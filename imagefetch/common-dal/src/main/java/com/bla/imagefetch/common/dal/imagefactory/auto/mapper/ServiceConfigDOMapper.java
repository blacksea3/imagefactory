package com.bla.imagefetch.common.dal.imagefactory.auto.mapper;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table service_config.
 * 服务配置表
 */
public interface ServiceConfigDOMapper{

    /**
     * desc:不带Id插入表:service_config.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO service_config( ID ,NAME ,SYS_NAME ,BEAN_NAME ,BEAN_TYPE ,EXT_INFO ,GMT_CREATE ,GMT_MODIFIED )VALUES( null , #{name,jdbcType=VARCHAR} , #{sysName,jdbcType=VARCHAR} , #{beanName,jdbcType=VARCHAR} , #{beanType,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithoutID(ServiceConfigDO entity);
    /**
     * desc:带Id插入表:service_config.<br/>
     * descSql =  <![CDATA[ INSERT INTO service_config( ID ,NAME ,SYS_NAME ,BEAN_NAME ,BEAN_TYPE ,EXT_INFO ,GMT_CREATE ,GMT_MODIFIED )VALUES( #{id,jdbcType=INTEGER} , #{name,jdbcType=VARCHAR} , #{sysName,jdbcType=VARCHAR} , #{beanName,jdbcType=VARCHAR} , #{beanType,jdbcType=VARCHAR} , #{extInfo,jdbcType=VARCHAR} , now() , now() ) ]]>
     * @param entity entity
     * @return Long
     */
    Long insertWithID(ServiceConfigDO entity);
    /**
     * desc:根据Id全量更新表:service_config.<br/>
     * descSql =  UPDATE service_config SET GMT_MODIFIED = now() ,NAME = #{name,jdbcType=VARCHAR} ,SYS_NAME = #{sysName,jdbcType=VARCHAR} ,BEAN_NAME = #{beanName,jdbcType=VARCHAR} ,BEAN_TYPE = #{beanType,jdbcType=VARCHAR} ,EXT_INFO = #{extInfo,jdbcType=VARCHAR} WHERE ID = #{id,jdbcType=INTEGER}
     * @param entity entity
     * @return Long
     */
    Long updateAll(ServiceConfigDO entity);
    /**
     * desc:根据Id删除数据:service_config.<br/>
     * descSql =  <![CDATA[ DELETE FROM service_config WHERE ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return Long
     */
    Long deleteById(Integer id);
    /**
     * desc:根据Id获取数据:service_config.<br/>
     * descSql =  SELECT * FROM service_config WHERE <![CDATA[ ID = #{id,jdbcType=INTEGER} ]]>
     * @param id id
     * @return ServiceConfigDO
     */
    ServiceConfigDO queryById(Integer id);
    /**
     * desc:根据普通索引Name获取数据:task_detail.<br/>
     * descSql =  SELECT * FROM service_config WHERE <![CDATA[ NAME = #{name,jdbcType=VARCHAR} ]]>
     * @param name name
     * @return ServiceConfigDO
     */
    ServiceConfigDO queryByName(@Param("name")String name);
}
