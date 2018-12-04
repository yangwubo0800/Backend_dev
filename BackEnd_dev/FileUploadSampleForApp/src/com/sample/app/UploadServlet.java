package com.sample.app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadFile")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// �ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "upload";

	// �ϴ�����
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 300; // 300MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 400; // 400MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 500; // 500MB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("*****************" + request.getContextPath());
		// �����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ������ʱ�洢Ŀ¼
		// factory.setRepository(new File(System.getProperty("user.dir")));
		factory.setRepository(new File(request.getContextPath()));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// ���Ĵ���
		upload.setHeaderEncoding("UTF-8");

		String webRoot = getServletContext().getRealPath("");
		System.out.println("=====webRoot="+webRoot);
		if (!webRoot.endsWith("/") && !webRoot.endsWith("\\")) {
			webRoot += File.separator;
		}

		String uploadPath = UPLOAD_DIRECTORY;

		// ���Ŀ¼�������򴴽�
		File uploadDir = new File(webRoot + uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			// ���������������ȡ�ļ�����
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// ����������
				for (FileItem item : formItems) {
					// �����ڱ��е��ֶ�
					if (item.isFormField()) {
						continue;
					}

					String fileName = new File(item.getName()).getName();
					System.out.println("=====fileName="+fileName);
					
					String filePath = uploadPath + File.separator + fileName;
					System.out.println("=====filePath="+filePath);

					// �����ļ���Ӳ��
					File storeFile = new File(webRoot + filePath);
					item.write(storeFile);	
					request.setAttribute("message", "�ļ��ϴ��ɹ�!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "������Ϣ: " + ex.getMessage());
		}
		
		// ��ת�� message.jsp
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

	}

}
