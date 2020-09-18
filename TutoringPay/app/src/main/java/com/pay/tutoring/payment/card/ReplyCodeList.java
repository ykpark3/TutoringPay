package com.pay.tutoring.payment.card;

import java.util.HashMap;
import java.util.Map;

public class ReplyCodeList {

    Map<String,Object> replyMap = new HashMap<String,Object>();

    public ReplyCodeList(){
        replyMap.put("0000 ","정상");
        replyMap.put("T001","은행 무응답(처리결과조회 요망) ");
        replyMap.put("C001 ","필수 항목 누락(기관코드)");
        replyMap.put("C002","필수 항목 누락(고객ID)");
        replyMap.put("C003 ","필수 항목 누락(거래구분코드)");
        replyMap.put("C004","필수 항목 누락(거래일자) ");
        replyMap.put("C005 ","필수 항목 누락(거래시간)");
        replyMap.put("C006","필수 항목 누락(거래일련번호) ");
        replyMap.put("C007 ","필수 항목 누락(거래금액)");
        replyMap.put("C008","필수 항목 누락(원거래일자) ");
        replyMap.put("C009 ","필수 항목 누락(원거래일련번호)");
        replyMap.put("C010","필수 항목 누락(원거래결제금액) ");
        replyMap.put("C011 ","필수 항목 누락(취소요청금액)");
        replyMap.put("C012","필수 항목 누락(은행코드) ");
        replyMap.put("C013 ","필수 항목 누락(계좌번호)");
        replyMap.put("C014","필수 항목 누락(원거래금액) ");
        replyMap.put("C031 ","DATA 길이 오류(기관코드)");
        replyMap.put("C032","DATA 길이 오류(고객ID)");
        replyMap.put("C033 ","DATA 길이 오류(거래구분코드)");
        replyMap.put("C034","DATA 길이 오류(거래일자) ");
        replyMap.put("C035 ","DATA 길이 오류(거래시간)");
        replyMap.put("C036","DATA 길이 오류(거래일련번호) ");
        replyMap.put("C037 ","DATA 길이 오류(거래금액)");
        replyMap.put("C038","DATA 길이 오류(원거래일자) ");
        replyMap.put("C039 ","DATA 길이 오류(원거래일련번호)");
        replyMap.put("C040","DATA 길이 오류(원거래결제금액) ");
        replyMap.put("C041 ","DATA 길이 오류(취소요청금액)");
        replyMap.put("C042","DATA 길이 오류(은행코드) ");
        replyMap.put("C043 ","DATA 길이 오류(계좌번호)");
        replyMap.put("C044","DATA 길이 오류(원거래금액) ");
        replyMap.put("C060 ","거래구분코드 확인 요망 ");
        replyMap.put("C061 ","은행코드 확인 요망");
        replyMap.put("C062","계좌번호 확인 요망 ");
        replyMap.put("C063 ","금액 확인 요망");
        replyMap.put("C064","원거래 없음 ");
        replyMap.put("C065 ","원거래 상이");
        replyMap.put("C066","중복 거래일련번호 ");
        replyMap.put("C081 ","잔액부족");
        replyMap.put("C082","기관코드 확인 요망 ");
        replyMap.put("C083 ","한도초과");
        replyMap.put("C084","기 취소된 거래 ");
        replyMap.put("C085 ","원거래 실패");
        replyMap.put("C086","원거래 처리 중");

    }

    public String getReplyDescription(String str)
    {
        String description = (String) replyMap.get(str);
        return description;
    }
}
