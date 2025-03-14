package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.ProductMapper;
import kr.project.linme.models.Product;
import kr.project.linme.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    /**
     * 상품 추가
     */
    @Override
    public Product addItem(Product input) throws Exception {
        int rows = 0;

        try {
            rows = productMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return productMapper.selectItem(input);
    }


    /**
     * 상품 수정
     */
    @Override
    public Product editItem(Product input) throws Exception {
        int rows = 0;

        try {
            rows = productMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return productMapper.selectItem(input);
    }

    /**
     * 상품 삭제
     */
    @Override
    public int deleteItem(Product input) throws Exception {
        int rows = 0;

        try {
            rows = productMapper.delete(input);

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
     * 상품 조회
     */
    @Override
    public Product getItem(Product input) throws Exception {
        Product output = null;

        try {
            output = productMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("상품 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 상품 목록 조회
     */
    @Override
    public List<Product> getList(Product input) throws Exception {
        List<Product> output = null;

        try {
            output = productMapper.selectList(input);
        } catch (Exception e) {
            log.error("상품 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 조회 상품 개수 조회
     */
    @Override
    public int getCount(Product input) throws Exception {
        int output = 0;

        try {
            int categoryId = input.getCategoryId();
            output = productMapper.selectCount(categoryId);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }
        return output;
    }

    /**
     * 카테고리별 상품 조회
     */
    @Override
    public List<Product> getProductsByCategory(int categoryId) throws Exception {
        List<Product> products = null;

        try {
            products = productMapper.selectByCategory(categoryId);
            
        } catch (Exception e) {
            log.error("카테고리별 상품 조회에 실패했습니다.", e);
            throw e;
        }

        return products;
    }

    /**
     * 상품 상세페이지 조회
     */
    @Override
    public Product getProductById(int productId) throws Exception {
        return productMapper.selectById(productId);
    }

}
