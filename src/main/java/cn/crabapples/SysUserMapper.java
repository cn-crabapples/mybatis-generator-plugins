package cn.crabapples;

import cn.crabapples.SysUser;
import cn.crabapples.SysUserExample;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser row);

    int insertSelective(SysUser row);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser row);

    int updateByPrimaryKey(SysUser row);
}