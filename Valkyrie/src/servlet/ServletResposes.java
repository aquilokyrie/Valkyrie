/**
 * 
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import assist.Logger;
import core.HttpRequest;
import core.HttpResponse;

/**
 * @author 春平
 *
 */
public class ServletResposes extends HttpResponse implements ServletResponse {
	
	
	private PrintWriter writer;
	private String contentType;
	
	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#getWriter()
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		this.writer = new PrintWriter(super.getCom().getOutput(),true);
		return this.writer;
	}
	

	/**
	 * @param request
	 */
	public ServletResposes(HttpRequest request) {
		super(request);
		// TODO 自动生成的构造函数存根
		
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#flushBuffer()
	 */
	@Override
	public void flushBuffer() throws IOException {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#getBufferSize()
	 */
	@Override
	public int getBufferSize() {
		// TODO 自动生成的方法存根
		return 0;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#getLocale()
	 */
	@Override
	public Locale getLocale() {
		// TODO 自动生成的方法存根
		return null;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#getOutputStream()
	 */
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#isCommitted()
	 */
	@Override
	public boolean isCommitted() {
		// TODO 自动生成的方法存根
		return false;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#reset()
	 */
	@Override
	public void reset() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#resetBuffer()
	 */
	@Override
	public void resetBuffer() {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#setBufferSize(int)
	 */
	@Override
	public void setBufferSize(int arg0) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#setContentLength(int)
	 */
	@Override
	public void setContentLength(int arg0) {
		// TODO 自动生成的方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
	 */
	@Override
	public void setContentType(String arg0) {
		this.contentType = arg0;
		String message = " Content-Type:text/html ";
		try {
			super.getCom().getOutput().write(message.getBytes());
		} catch (IOException e) {
			Logger.info("servlet服务时设置ContentType出错");
		}
		
	}

	/* （非 Javadoc）
	 * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
	 */
	@Override
	public void setLocale(Locale arg0) {
		// TODO 自动生成的方法存根

	}

}
