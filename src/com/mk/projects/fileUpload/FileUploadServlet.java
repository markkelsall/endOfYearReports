package com.mk.projects.fileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    public void init( ){
       // Get the file location where it would be stored.
       filePath = getServletContext().getInitParameter("file-upload"); 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if( !isMultipart ){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>"); 
			out.println("</body>");
			out.println("</html>");
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		//Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		//maximum file size to be uploaded.
		upload.setSizeMax( maxFileSize );

		try { 
			//Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			//Process the uploaded file items
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet upload</title>");  
			out.println("</head>");
			out.println("<body>");
			String fileName = "";
			
			while (i.hasNext ()) {
				FileItem fi = (FileItem)i.next();
				if (!fi.isFormField ()) {
					//Get the uploaded file parameters

					String fieldName = fi.getFieldName();
					fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					//Write the file
					
					if(fileName.lastIndexOf("\\") >= 0) {
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
					} else {
						file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					fi.write(file);
					out.println("Uploaded Filename: " + fileName + "<br>");
					out.println("Uploaded in location: "+filePath);
				}
			}
			out.println("</body>");
			out.println("</html>");

			FileProcessor fileProcessor = new FileProcessor();
			System.out.println("file name: "+fileName.substring(fileName.lastIndexOf("\\")));
			String s = fileName.substring(fileName.lastIndexOf("\\"));
			System.out.println(filePath);
			System.out.println(s);
			String fileP = filePath.concat(s+"\\");

			System.out.println(fileP);
			rd.read(fileP);
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
