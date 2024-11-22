package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Category;

public interface CategoryService {

    /**
     * 카테고리 추가
     * @param params
     * @return
     * @throws Exception
     */
    public Category addItem(Category params) throws Exception;


    /**
     * 카테고리 수정
     * @param params
     * @return
     * @throws Exception
     */
    public Category editItem(Category params) throws Exception;


    /**
     * 카테고리 삭제
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Category params) throws Exception;


    /**
     * 단일카테고리 조회
     * @param params
     * @return
     * @throws Exception
     */
    public Category getItem(Category params) throws Exception;


    /**
     * 카테고리 리스트 조회 
     * @param params
     * @return
     * @throws Exception
     */
    public List<Category> getList(Category params) throws Exception;


    /**
     * 특정조건에 맞는 카테고리의 개수 조회
     * @param params
     * @return
     * @throws Exception
     */
    public int getCount(Category params) throws Exception;
}
