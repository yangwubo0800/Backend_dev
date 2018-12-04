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
	
	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
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
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		// factory.setRepository(new File(System.getProperty("user.dir")));
		factory.setRepository(new File(request.getContextPath()));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		String webRoot = getServletContext().getRealPath("");
		System.out.println("=====webRoot="+webRoot);
		if (!webRoot.endsWith("/") && !webRoot.endsWith("\\")) {
			webRoot += File.separator;
		}

		String uploadPath = UPLOAD_DIRECTORY;

		// 如果目录不存在则创建
		File uploadDir = new File(webRoot + uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (item.isFormField()) {
						continue;
					}

					String fileName = new File(item.getName()).getName();
					System.out.println("=====fileName="+fileName);
					
					String filePath = uploadPath + File.separator + fileName;
					System.out.println("=====filePath="+filePath);

					// 保存文件到硬盘
					File storeFile = new File(webRoot + filePath);
					item.write(storeFile);	
					request.setAttribute("message", "文件上传成功!");
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "错误信息: " + ex.getMessage());
		}
		
		// 跳转到 message.jsp
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

	}

}
