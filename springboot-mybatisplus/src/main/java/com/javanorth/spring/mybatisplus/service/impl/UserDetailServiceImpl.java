package com.javanorth.spring.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javanorth.spring.mybatisplus.dao.UserDetailDao;
import com.javanorth.spring.mybatisplus.entity.UserDetail;
import com.javanorth.spring.mybatisplus.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;

    @Override
    public boolean save(UserDetail entity) {
        int flag = userDetailDao.insert(entity);
        return true;
    }

    @Override
    public boolean saveBatch(Collection<UserDetail> entityList) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<UserDetail> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserDetail> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserDetail> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        userDetailDao.deleteById(id);
        return true;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<UserDetail> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(UserDetail entity) {
        return false;
    }

    @Override
    public boolean update(Wrapper<UserDetail> updateWrapper) {
        return false;
    }

    @Override
    public boolean update(UserDetail entity, Wrapper<UserDetail> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserDetail> entityList) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserDetail> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserDetail entity) {
        return false;
    }

    @Override
    public UserDetail getById(Serializable id) {
        return null;
    }

    @Override
    public List<UserDetail> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<UserDetail> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public UserDetail getOne(Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public UserDetail getOne(Wrapper<UserDetail> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserDetail> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int count(Wrapper<UserDetail> queryWrapper) {
        return 0;
    }

    @Override
    public List<UserDetail> list(Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public List<UserDetail> list() {
        return null;
    }

    @Override
    public <E extends IPage<UserDetail>> E page(E page, Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public <E extends IPage<UserDetail>> E page(E page) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    @Override
    public List<Object> listObjs() {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<UserDetail> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<UserDetail> queryWrapper) {
        return null;
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return null;
    }

    @Override
    public BaseMapper<UserDetail> getBaseMapper() {
        return null;
    }

    @Override
    public QueryChainWrapper<UserDetail> query() {
        return null;
    }

    @Override
    public LambdaQueryChainWrapper<UserDetail> lambdaQuery() {
        return null;
    }

    @Override
    public UpdateChainWrapper<UserDetail> update() {
        return null;
    }

    @Override
    public LambdaUpdateChainWrapper<UserDetail> lambdaUpdate() {
        return null;
    }

    @Override
    public boolean saveOrUpdate(UserDetail entity, Wrapper<UserDetail> updateWrapper) {
        return false;
    }
}
