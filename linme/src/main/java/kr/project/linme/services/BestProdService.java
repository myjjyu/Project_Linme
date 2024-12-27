package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.BestProd;

public interface BestProdService {

    public List<BestProd> selectMList() throws Exception;

    public List<BestProd> selectWList() throws Exception;

    public int insert(BestProd input) throws Exception;

}
