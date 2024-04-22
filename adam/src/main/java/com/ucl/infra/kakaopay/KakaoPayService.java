package com.ucl.infra.kakaopay;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ucl.common.constants.Commvar;
import com.ucl.infra.payment.PaymentService;

import jakarta.servlet.http.HttpSession;

@Service

public class KakaoPayService {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	KakaoPayDao dao;
	
	private static final String Host = "https://kapi.kakao.com";
	
	@Value("${kakaopay_admin_key}")
    private String kakaoAdminKey;

	private KakaoPayVo kakaoPayVo;
    private KakaoPayDto kakaoPayDto;
    private KakaoPayApprovalDto kakaoPayApprovalDto;
    private CancelDto kakaoPayCancelDto;

    // 카카오페이 결제등록용 순번
 	public int paymentInsertPaySeq(KakaoPayDto dto) {
 		return dao.paymentInsertPaySeq(dto);
 	};
 	
    // 결제요청
    public String kakaoPayReady(KakaoPayVo vo) {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성
        
        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoAdminKey); // admin key
        headers.add("Accept"       , "application/json");
        headers.add("Content-type" , "application/x-www-form-urlencoded;charset=utf-8");
        
        // Server Request Body : 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid"             , "TC0ONETIME");				// 가맹점 코드 - 테스트용
        params.add("partner_order_id", vo.getPartner_order_id());	// 주문 번호
        params.add("partner_user_id" , vo.getPartner_user_id());	// 회원 아이디
        params.add("item_name"       , vo.getItem_name());			// 상품 명
        params.add("quantity"        , vo.getQuantity());			// 상품 수량
        params.add("total_amount"    , vo.getTotal_amount());		// 상품 가격
        params.add("tax_free_amount" , vo.getTax_free_amount());	// 상품 비과세 금액
        params.add("approval_url"    , vo.getApproval_url());		// 성공시 url
        params.add("cancel_url"      , vo.getCancel_url());			// 취소시 url
        params.add("fail_url"        , vo.getFail_url());			// 실패시 url
        
        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
        	kakaoPayVo = vo;
            kakaoPayDto = restTemplate.postForObject(new URI(Host + "/v1/payment/ready"), body, KakaoPayDto.class);
            return kakaoPayDto.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return Commvar.PATH_PRODUCT + "/productUsrCheckOut";
    }
    
    // 결제결과정보
    public KakaoPayApprovalDto kakaoPayInfo(String pg_token) {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성
        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoAdminKey); // admin key
        headers.add("Accept"       , "application/json");
        headers.add("Content-type" , "application/x-www-form-urlencoded;charset=utf-8");
        
        // Server Request Body : 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid"             , "TC0ONETIME");						// 가맹점 코드 - 테스트용
        params.add("tid"             , kakaoPayDto.getTid());				// 결제고유번호
        params.add("partner_order_id", kakaoPayVo.getPartner_order_id());	// 주문 번호
        params.add("partner_user_id" , kakaoPayVo.getPartner_user_id());	// 회원 아이디
        params.add("pg_token"        , pg_token);							// 카카오 토큰
        params.add("total_amount"    , kakaoPayVo.getTotal_amount());		// 결제금액
        
        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalDto = restTemplate.postForObject(new URI(Host + "/v1/payment/approve"), body, KakaoPayApprovalDto.class);
            kakaoPayApprovalDto.setTax_free_amount(kakaoPayApprovalDto.getAmount().getTax_free());
            kakaoPayApprovalDto.setVat_amount(kakaoPayApprovalDto.getAmount().getVat());
            kakaoPayApprovalDto.setResultInfo(kakaoPayApprovalDto.toString());
            
            //System.out.println(kakaoPayApprovalDto.toString());

            /*
            httpSession.setAttribute("sessTid"    , kakaoPayDto.getTid());							// 결제고유번호
            httpSession.setAttribute("sessTotal"  , kakaoPayApprovalDto.getAmount().getTotal());	// 결제금액
            httpSession.setAttribute("sessVat"    , kakaoPayApprovalDto.getAmount().getVat());		// 부가세액
            httpSession.setAttribute("sessTaxFree", kakaoPayApprovalDto.getAmount().getTax_free());	// 비과세액
            */
            
            // 결제정보db저장
            dao.paymentUpdateKakaoPay(kakaoPayApprovalDto);
            
            return kakaoPayApprovalDto;
        
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // 결제취소
    public CancelDto kakaoPayCancel(HttpSession httpSession) {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성
        // Server Request Header : 서버 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoAdminKey); // admin key
        headers.add("Accept", "application/json");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // Server Request Body : 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid"                   , "TC0ONETIME");											// 가맹점 코드 - 테스트용
        params.add("tid"                   , httpSession.getAttribute("sessTid").toString()); 		// 환불할 결제 고유 번호
        params.add("cancel_amount"         , httpSession.getAttribute("sessTotal").toString());		// 환불 금액
        params.add("cancel_vat_amount"     , httpSession.getAttribute("sessVat").toString());		// 환불 부가세
        params.add("cancel_tax_free_amount", httpSession.getAttribute("sessTaxFree").toString());	// 환불 비과세 금액
        
        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
        	kakaoPayCancelDto = restTemplate.postForObject(new URI(Host + "/v1/payment/cancel"), body, CancelDto.class);
        	kakaoPayCancelDto.setResultInfo(kakaoPayCancelDto.toString());
        	return kakaoPayCancelDto;

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return null;
    }  
    
    
}
