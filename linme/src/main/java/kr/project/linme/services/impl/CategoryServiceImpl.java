package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.CategoryMapper;
import kr.project.linme.models.Category;
import kr.project.linme.services.CategoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category addItem(Category input) throws Exception {
        int rows = 0;

        try {
            rows = categoryMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return categoryMapper.selectItem(input);
    }

    @Override
    public Category editItem(Category input) throws Exception {
        int rows = 0;

        try {
            rows = categoryMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return categoryMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Category input) throws Exception {
        int rows = 0;

        try {
            rows = categoryMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public Category getItem(Category input) throws Exception {
        Category output = null;

        try {
            output = categoryMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("카테고리 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public List<Category> getList(Category input) throws Exception {
        List<Category> output = null;

        try {
            output = categoryMapper.selectList(input);
        } catch (Exception e) {
            log.error("카테고리 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Category input) throws Exception {
        int output = 0;

        try {
            output = categoryMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}