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
 * @author ��ƽ
 *
 */
public class ServletResposes extends HttpResponse implements ServletResponse {
	
	
	private PrintWriter writer;
	private String contentType;
	
	/* ���� Javadoc��
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
		// TODO �Զ����ɵĹ��캯�����
		
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#flushBuffer()
	 */
	@Override
	public void flushBuffer() throws IOException {
		// TODO �Զ����ɵķ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#getBufferSize()
	 */
	@Override
	public int getBufferSize() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		// TODO �Զ����ɵķ������
		return null;
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#getLocale()
	 */
	@Override
	public Locale getLocale() {
		// TODO �Զ����ɵķ������
		return null;
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#getOutputStream()
	 */
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO �Զ����ɵķ������
		return null;
	}

	

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#isCommitted()
	 */
	@Override
	public boolean isCommitted() {
		// TODO �Զ����ɵķ������
		return false;
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#reset()
	 */
	@Override
	public void reset() {
		// TODO �Զ����ɵķ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#resetBuffer()
	 */
	@Override
	public void resetBuffer() {
		// TODO �Զ����ɵķ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#setBufferSize(int)
	 */
	@Override
	public void setBufferSize(int arg0) {
		// TODO �Զ����ɵķ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#setContentLength(int)
	 */
	@Override
	public void setContentLength(int arg0) {
		// TODO �Զ����ɵķ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
	 */
	@Override
	public void setContentType(String arg0) {
		this.contentType = arg0;
		String message = " Content-Type:text/html ";
		try {
			super.getCom().getOutput().write(message.getBytes());
		} catch (IOException e) {
			Logger.info("servlet����ʱ����ContentType����");
		}
		
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
	 */
	@Override
	public void setLocale(Locale arg0) {
		// TODO �Զ����ɵķ������

	}

}
