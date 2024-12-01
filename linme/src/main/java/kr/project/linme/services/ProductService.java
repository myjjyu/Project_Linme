package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Product;





public interface ProductService {
    /**
     * 상품추가
     * @param params
     * @return
     * @throws Exception
     */
    public Product addItem(Product params) throws Exception;


    /**
     * 상품 수정
     * @param params
     * @return
     * @throws Exception
     */
    public Product editItem(Product params) throws Exception;


    /**
     * 상품삭제
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Product params) throws Exception;


    /**
     * 상품 단일조회
     * @param params
     * @return
     * @throws Exception
     */
    public Product getItem(Product params) throws Exception;


    /**
     * 상품 리스트 조회
     * @param params
     * @return
     * @throws Exception
     */
    public List<Product> getList(Product params) throws Exception;


    /**
     * 특정 조건에 맞는 상품의 개수 조회
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Product params) throws Exception;


     /**
     * 카테고리 ID로 상품 리스트 조회
     * @param categoryId
     * @return
     * @throws Exception
     */
    public List<Product> getProductsByCategory(int categoryId) throws Exception;


    /**
     * 상품 ID로 상품 조회(상세페이지)
     * @param productId
     * @return
     * @throws Exception
     */
    public Product getProductById(int productId) throws Exception;
}


