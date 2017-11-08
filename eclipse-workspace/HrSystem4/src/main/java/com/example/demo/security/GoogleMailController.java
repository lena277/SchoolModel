package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.repositories.EmployeeRepository;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

@Controller
@RestController
public class GoogleMailController {

	private static final String APPLICATION_NAME = "GmailAlexa";
	private static HttpTransport httpTransport;
	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.gmail.Gmail client;

	@Autowired
	EmployeeRepository repo;
	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	Credential credential;

	@Value("${gmail.client.clientId}")
	private String clientId;

	@Value("${oauth2.scope}")
	private String scope;

	@Value("${oauth2.userInfoUri}")
	private String userInfoUri;

	@Value("${gmail.client.clientSecret}")
	private String clientSecret;

	@Value("${oauth2.userAuthorizationUri}")
	private String userAuthorizationUri;

	@Value("${oauth2.accessTokenUri}")
	private String accessTokenUri;

	@Value("${oauth2.tokenName:authorization_code}")
	private String tokenName;

	@Value("${gmail.client.redirectUri}")
	private String redirectUri;

	HttpServletRequest re;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {

		return new RedirectView(authorize());

	}

	@RequestMapping(value = "/login/gmailCallback", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@RequestParam(value = "code") String code) throws Exception {

		HttpTransport transport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();

		try {
			GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory,
					"https://www.googleapis.com/oauth2/v4/token", clientId, clientSecret, code, redirectUri).execute();

			String accessToken = response.getAccessToken();
			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

			GoogleIdToken idToken = response.parseIdToken();
			GoogleIdToken.Payload payload = idToken.getPayload();

			String userId = payload.getSubject();
			String email = payload.getEmail();

			if (repo.findByEmailIgnoreCase(email) == null) {
				SecurityContextHolder.clearContext();
				return new RedirectView(authorize());
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		RedirectView v = new RedirectView(hi());
		return v;
	}

	AuthorizationCodeRequestUrl authorizationUrl;

	private String authorize() throws Exception {
		if (flow == null) {
			AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
			details.setId("google-oauth-client");
			details.setClientId(clientId);
			details.setClientSecret(clientSecret);
			details.setUserAuthorizationUri(userAuthorizationUri);
			details.setAccessTokenUri(accessTokenUri);
			details.setTokenName(tokenName);
			String commaSeparatedScopes = scope;
			details.setScope(parseScopes(commaSeparatedScopes));

			details.setAuthenticationScheme(AuthenticationScheme.query);
			details.setClientAuthenticationScheme(AuthenticationScheme.form);
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);

			clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					parseScopes(commaSeparatedScopes)).build();

		}

		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);

		return authorizationUrl.build();

	}

	private List<String> parseScopes(String commaSeparatedScopes) {
		List<String> scopes = new LinkedList<>();
		Collections.addAll(scopes, commaSeparatedScopes.split(","));
		return scopes;
	}

	public String hi() {
		return "http://localhost:9000/employees";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	String home(java.security.Principal user) {
		return "Hello " + user;
	}

}
