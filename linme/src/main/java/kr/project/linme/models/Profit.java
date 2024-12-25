package kr.project.linme.models;

import lombok.Data;

@Data
public class Profit {
    
    public int saleId;
    public int categoryId;
    public String productName;
    public int orderCount;
    public int salePrice;
    public String regDate;

}

