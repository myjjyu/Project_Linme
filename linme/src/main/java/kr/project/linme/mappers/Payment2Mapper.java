package kr.project.linme.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import kr.project.linme.models.Payment;

@Mapper
public interface Payment2Mapper {
    // 주소 변경(배송지명)
        @Insert("INSERT INTO member (" +
                "addrName) " +
                "VALUES (#{addrName})")
        @Options(useGeneratedKeys = true, keyProperty = "paymentId", keyColumn = "payment_id")
        public int saveAddressName(Payment input);
}
