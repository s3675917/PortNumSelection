package NumberSelection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import com.sun.mail.imap.protocol.MailboxInfo;




/**
 * Servlet implementation class SelectionServlet
 */
@WebServlet("/SelectionServlet")
public class SelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectionServlet() {
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
		String pathString = this.getServletContext().getRealPath("port.txt");
		String id = request.getParameter("id");
		String portNum1 =  request.getParameter("portNum1");
		String portNum2 =  request.getParameter("portNum2");
		String string = null;
		try {
			string = Control.app01.check(pathString, id, portNum1,portNum2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("loginInfo", string);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		doGet(request, response);
	}

}
