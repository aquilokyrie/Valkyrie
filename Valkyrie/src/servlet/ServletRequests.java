/**
 * 
 */
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import core.Communication;
import core.HttpRequest;

/**
 * @author 春平
 * 封装请求动态资源的HTTP请求
 */
public class ServletRequests extends HttpRequest implements ServletRequest {

	/**
	 * @param message
	 * @param c
	 */
	public ServletRequests(String message, Communication c) {
		super(message, c);
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	@Override
	public Enumeration getAttributeNames() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	@Override
	public int getContentLength() {
		// TODO 自动生成的方法存根
		return 0;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	@Override
	public String getContentType() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	@Override
	public Locale getLocale() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	@Override
	public Enumeration getLocales() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	@Override
	public Map getParameterMap() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	@Override
	public Enumeration getParameterNames() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	@Override
	public String[] getParameterValues(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	@Override
	public String getProtocol() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	@Override
	public BufferedReader getReader() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	@Override
	public String getRealPath(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	@Override
	public String getRemoteAddr() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	@Override
	public String getRemoteHost() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	@Override
	public String getScheme() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	@Override
	public String getServerName() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	@Override
	public int getServerPort() {
		// TODO 自动生成的方法存根
		return 0;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	@Override
	public boolean isSecure() {
		// TODO 自动生成的方法存根
		return false;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String arg0) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO 自动生成的方法存根

	}

}
