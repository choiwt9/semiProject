package semiPro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class updatepwd
 */
@WebServlet("/updatepwd")
public class updatepwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatepwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPwd = request.getParameter("userPwd");
		 String newPassword = request.getParameter("newPassword");
		 HttpSession session = request.getSession();
		 
		    Member user = (Member)session.getAttribute("loginUser");
		    String userId = user.getUserId();
		    
		    Member updateMem = new MemberService().updatePassword(userId, userPwd, newPassword);    
		    
		       if(updateMem == null) {//蹂�寃� �떎�뙣 �떆 �뿉�윭 �럹�씠吏�濡� �쓳�떟
		    	request.setAttribute("errorMsg", "鍮꾨�踰덊샇 蹂�寃쎌뿉 �떎�뙣�뻽�뒿�땲�떎");
		    	 request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response); 
		       }else {//蹂�寃� �꽦怨듯뻽�쓣 �븣 留덉씠�럹�씠吏� �씠�룞(url �옱�슂泥�), �꽦怨듯뻽�뒿�땲�떎 硫붿떆吏� �븣由쇱갹 �쓣�썙二쇨린
				session.setAttribute("alertMsg", "鍮꾨�踰덊샇 蹂�寃쎌뿉 �꽦怨듯뻽�뒿�땲�떎");
				
				session.setAttribute("loginUser", updateMem);
				
				//�슂泥��븷 url => /jsp/myPage.me
				response.sendRedirect(request.getContextPath()+"");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
