1. SSO Filter in /Node/etc/tomcat/web.xml 

2. Workaround for now in CNPortletapp/WEB-INF/web.xml

 <servlet-mapping>
          <servlet-name>JerseyServlet</servlet-name>
          <url-pattern>/restnosso/*</url-pattern>
  </servlet-mapping>
          
http://www.gentics.com/Content.Node/guides/rest_auth.html#sso-with-cas