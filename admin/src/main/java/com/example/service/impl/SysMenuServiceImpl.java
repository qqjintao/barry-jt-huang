package com.example.service.impl;

import com.example.constant.Constants;
import com.example.constant.SysConstants;
import com.example.dao.SysMenuDao;
import com.example.dto.SysMenuDTO;
import com.example.mapper.SysMenuMapper;
import com.example.mapper.SysRoleMenuMapper;
import com.example.model.*;
import com.example.service.ISysMenuService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import com.example.vo.MetaVo;
import com.example.vo.RouterVo;
import com.example.vo.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author barry.jt.huang
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Set<String> getMenuPermission(Long id) {
        List<String> perms = sysMenuDao.selectMenuPermsByUserId(id);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenuDTO> selectMenuTreeByUserId(Long userId) {
        List<SysMenuDTO> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            menus = sysMenuDao.selectMenuTreeAll();
        } else {
            menus = sysMenuDao.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenuDTO> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuDTO menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuNameZh(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
            List<SysMenuDTO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && Constants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuNameZh(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache())));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        Long userId = SecurityUtils.getUserId();
        if (SecurityUtils.isAdmin(userId)) {
            SysMenuExample example = new SysMenuExample();
            SysMenuExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotEmpty(menu.getStatus())) {
                criteria.andStatusEqualTo(menu.getStatus());
            }
            if (StringUtils.isNotEmpty(menu.getVisible())) {
                criteria.andVisibleEqualTo(menu.getVisible());
            }
            if (StringUtils.isNotEmpty(menu.getMenuNameZh())) {
                criteria.andMenuNameZhLike(StringUtils.getSqlLike(menu.getMenuNameZh()));
            }
            if (StringUtils.isNotEmpty(menu.getMenuNameEn())) {
                criteria.andMenuNameEnLike(StringUtils.getSqlLike(menu.getMenuNameEn()));
            }
            menuList = sysMenuMapper.selectByExample(example);
        } else {
            menuList = sysMenuDao.selectMenuListByUserId(menu, userId);
        }
        return menuList;
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param id 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenu selectMenuById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
        List<TreeSelect> treeSelectList = getChildren(menus, 0L);
        return treeSelectList;
    }

    /**
     * 删除菜单管理信息
     *
     * @param id 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu) {
        menu.setCreateBy(SecurityUtils.getUserId());
        menu.setCreateTime(new Date());
        return sysMenuMapper.insertSelective(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenu menu) {
        menu.setUpdateBy(SecurityUtils.getUserId());
        menu.setUpdateTime(new Date());
        return sysMenuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param id 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long id) {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        long result = sysMenuMapper.countByExample(example);
        return result > 0 ? true : false;
    }

    /**
     * 查询菜单使用数量
     *
     * @param id 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long id)
    {
        SysRoleMenuExample example = new SysRoleMenuExample();
        SysRoleMenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdEqualTo(id);
        long result = sysRoleMenuMapper.countByExample(example);
        return result > 0 ? true : false;
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return sysMenuDao.selectMenuListByRoleId(roleId);
    }


    public List<TreeSelect> getChildren(List<SysMenu> menus, Long parentId) {
        List<TreeSelect> treeSelectList = new ArrayList<>();
        for (Iterator<SysMenu> iterator = menus.iterator(); iterator.hasNext(); ) {
            SysMenu dept = iterator.next();
            if (dept.getParentId().equals(parentId)) {
                TreeSelect treeSelect = new TreeSelect();
                treeSelect.setId(dept.getId());
                treeSelect.setLabel(dept.getMenuNameZh());
                treeSelect.setChildren(getChildren(menus, dept.getId()));
                treeSelectList.add(treeSelect);
            }
        }
        return treeSelectList;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuDTO> getChildPerms(List<SysMenuDTO> list, int parentId) {
        List<SysMenuDTO> returnList = new ArrayList<>();
        for (Iterator<SysMenuDTO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuDTO t = iterator.next();
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuDTO> list, SysMenuDTO t) {
        // 得到子节点列表
        List<SysMenuDTO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuDTO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuDTO> getChildList(List<SysMenuDTO> list, SysMenuDTO t) {
        List<SysMenuDTO> tlist = new ArrayList<>();
        Iterator<SysMenuDTO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuDTO n = it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuDTO> list, SysMenuDTO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuDTO menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenuDTO menu) {
        return menu.getParentId().intValue() == 0 && Constants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(Constants.NO_FRAME);
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuDTO menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && Constants.TYPE_DIR.equals(menu.getMenuType())
                && Constants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuDTO menu)
    {
        String component = Constants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu))
        {
            component = menu.getComponent();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
        {
            component = Constants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenuDTO menu)
    {
        return menu.getParentId().intValue() != 0 && Constants.TYPE_DIR.equals(menu.getMenuType());
    }
}
