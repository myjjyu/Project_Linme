package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.CategoryMapper;
import kr.project.linme.models.Category;
import kr.project.linme.services.CategoryService;
import lombok.extern.slf4j.Slf4j;

/**
 * 카테고리 데이터 관리 기능을 제공하기 위한 Service 계층에 대한 구현체
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 카테고리 데이터 저장하기
     */
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

    /**
     * 카테고리 데이터 수정하기
     */
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

    /**
     * 카테고리 데이터 삭제하기
     */
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

    /**
     * 카테고리 데이터 단일 조회하기
     */
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

    /**
     * 카테고리 데이터 목록 조회하기
     */
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

    /**
     * 카테고리 데이터가 저장되어 있는 갯수 조회
     */
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