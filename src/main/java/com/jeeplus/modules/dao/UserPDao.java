/**
 * 
 */
package com.jeeplus.modules.dao;

import com.jeeplus.modules.model.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 *
 */

@Mapper
public interface UserPDao {

//	@Param(value = "orgId") String orgId
	// 区域user下的集合
	public List<MapEntity> getUsers(@Param(value = "userId") String userId,@Param(value = "name") String name,@Param(value = "officeId") String officeId);


	public MapEntity getUsersById(@Param(value = "loginName") String loginName);



	void updateUser(MapEntity entity);

	void insertUser(MapEntity entity);

	void deleteUserById(@Param(value = "id") String id);

	void updateFalgById(@Param(value = "id") String id,@Param(value = "delFlag") String delFlag,@Param(value = "password") String password);

Integer 	updateSysPassword(@Param(value = "oldSysPassword") String oldSysPassword,@Param(value = "sysPassword") String sysPassword);
	 
}
