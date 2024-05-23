package com.ucl.infra.googlelogin;

public class GoogleRequest {
    private String clientId;    // 애플리케이션의 클라이언트 ID
    private String redirectUri; // Google 로그인 후 redirect 위치
    private String clientSecret;    // 클라이언트 보안 비밀
    private String responseType;    // Google OAuth 2.0 엔드포인트가 인증 코드를 반환하는지 여부
    private String scope;   // OAuth 동의범위
    private String code;
    private String accessType;  // 사용자가 브라우저에 없을 때 애플리케이션이 액세스 토큰을 새로 고칠 수 있는지 여부
    private String grantType;
    private String state;
    private String includeGrantedScopes;    // 애플리케이션이 컨텍스트에서 추가 범위에 대한 액세스를 요청하기 위해 추가 권한 부여를 사용
    private String loginHint;   // 애플리케이션이 인증하려는 사용자를 알고 있는 경우 이 매개변수를 사용하여 Google 인증 서버에 힌트를 제공
    private String prompt;  // default: 처음으로 액세스를 요청할 때만 사용자에게 메시지가 표시
    
    private GoogleRequest(GoogleRequestBuilder builder){
		this.clientId             = builder.clientId;
		this.redirectUri          = builder.redirectUri;
		this.clientSecret         = builder.clientSecret;
		this.responseType         = builder.responseType;
		this.scope                = builder.scope;
		this.code                 = builder.code;
		this.accessType           = builder.accessType;
		this.grantType            = builder.grantType;
		this.state                = builder.state;
		this.includeGrantedScopes = builder.includeGrantedScopes;
		this.loginHint            = builder.loginHint;
		this.prompt               = builder.prompt;
	}
    
    public static class GoogleRequestBuilder {
        private String clientId;    // 애플리케이션의 클라이언트 ID
        private String redirectUri; // Google 로그인 후 redirect 위치
        private String clientSecret;    // 클라이언트 보안 비밀
        private String responseType;    // Google OAuth 2.0 엔드포인트가 인증 코드를 반환하는지 여부
        private String scope;   // OAuth 동의범위
        private String code;
        private String accessType;  // 사용자가 브라우저에 없을 때 애플리케이션이 액세스 토큰을 새로 고칠 수 있는지 여부
        private String grantType;
        private String state;
        private String includeGrantedScopes;    // 애플리케이션이 컨텍스트에서 추가 범위에 대한 액세스를 요청하기 위해 추가 권한 부여를 사용
        private String loginHint;   // 애플리케이션이 인증하려는 사용자를 알고 있는 경우 이 매개변수를 사용하여 Google 인증 서버에 힌트를 제공
        private String prompt;  // default: 처음으로 액세스를 요청할 때만 사용자에게 메시지가 표시
		
        public GoogleRequestBuilder setClientId(String clientId) {
			this.clientId = clientId;
			return this;
		}
		public GoogleRequestBuilder setRedirectUri(String redirectUri) {
			this.redirectUri = redirectUri;
			return this;
		}
		public GoogleRequestBuilder setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}
		public GoogleRequestBuilder setResponseType(String responseType) {
			this.responseType = responseType;
			return this;
		}
		public GoogleRequestBuilder setScope(String scope) {
			this.scope = scope;
			return this;
		}
		public GoogleRequestBuilder setCode(String code) {
			this.code = code;
			return this;
		}
		public GoogleRequestBuilder setAccessType(String accessType) {
			this.accessType = accessType;
			return this;
		}
		public GoogleRequestBuilder setGrantType(String grantType) {
			this.grantType = grantType;
			return this;
		}
		public GoogleRequestBuilder setState(String state) {
			this.state = state;
			return this;
		}
		public GoogleRequestBuilder setIncludeGrantedScopes(String includeGrantedScopes) {
			this.includeGrantedScopes = includeGrantedScopes;
			return this;
		}
		public GoogleRequestBuilder setLoginHint(String loginHint) {
			this.loginHint = loginHint;
			return this;
		}
		public GoogleRequestBuilder setPrompt(String prompt) {
			this.prompt = prompt;
			return this;
		}

        public GoogleRequest build() {
            return new GoogleRequest(this);
        }
    }
}
