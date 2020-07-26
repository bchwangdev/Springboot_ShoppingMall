package controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.BoardBean;
import model.MemberBean;
import model.ShoppingDAO;
import model.SuBean;
import model.SuCartBean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class ShoppingController {

	ShoppingDAO shoppingDao;

	//설정취득
	public void setShoppingDao(ShoppingDAO shoppingDao) {
		this.shoppingDao = shoppingDao;
	}


	@RequestMapping("/index.do")//index.do라는 요청이 들어오면 실행되는 메소드
	public ModelAndView index(HttpSession session){//회원 가입 정보를 사용하기위하여 세션을 설정

		//데이터와 jsp를 리턴해주는 객체 생성
		ModelAndView mav = new ModelAndView();
		//회원 정보를 사용하기위하여 세션객체를 불러옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//세션을 이용하여 로그인처리
		if(mbean==null){//top.jsp에서 로그인 정보를 처리하기위한 소스
			mav.addObject("mbean" , null);
			mav.setViewName("ShoppingMain");			
		}else{
			mav.addObject("mbean" , mbean);
			mav.setViewName("ShoppingMain");		
		}

		return mav;		
	}



	//수작업 공구를 눌렀다면 자동으로 호출되는 메소드
	@RequestMapping("/sujak.do")
	public ModelAndView suJak(String num){

		//리턴할 객체 생성
		ModelAndView mav = new ModelAndView();
		//어느 요청이 들어왔는지에 대한 정보를 얻어와서 해당 model에 접근하여 데이터를 가져오도록
		if(num==null){ // 신제품을 누르거나 top에 있는 메뉴중 수작업 공구버튼을 누르면			
			List<SuBean> list = shoppingDao.getAllSutool();
			mav.addObject("list", list);
		}else { // 해당메뉴들을 선택시 사용되는 소스		
			List<SuBean> list = shoppingDao.getSelectSutool(num);
			mav.addObject("list", list);
		}	

		mav.addObject("center" ,"SujakCenter.jsp");
		mav.addObject("left", "SujakLeft.jsp");

		mav.setViewName("ShoppingMain");
		return mav;
	}

	//공구 하나의 정보를 보여주는 메소드
	@RequestMapping("/suinfo.do")
	public ModelAndView suInfo(int suno){

		SuBean sbean = shoppingDao.getOneSutool(suno);

		//View 쪽으로 데이터를 떠넘겨줌
		ModelAndView mav =new ModelAndView();
		mav.addObject("sbean", sbean);
		mav.addObject("center", "SuJakInfo.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//카트에 담기 버튼을 누르면 호출되는 메소드
	@RequestMapping("/sutoolcart.do")
	public ModelAndView sutoolCart(SuCartBean cartbean , HttpSession session ){
		//카트에 저장되는 빈클래스은 SuCartBean

		//카트를 사용하기위해서 Cart클래스를 등록
		Cart cart = (Cart) session.getAttribute("cart");
		//기존에 카트클래스가 없다면 
		if(cart == null){
			//카트 객체를 생성한후에 세션에 저장
			cart = new Cart();
			session.setAttribute("cart", cart);//내가 마트에서 나갈때까지 계속 이카트를 유지
		}
		//카트에 넘어온 상품을 추가
		cart.push(cartbean);

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", cartbean.getSuname()+" 의 상품을 " +cartbean.getSuqty()
				+ "개 카트에 추가했습니다." );
		mav.addObject("cart",cart);
		mav.addObject("center", "SuCartResult.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}


	//카트의 하나의 목록을 삭제 하는 메소드
	@RequestMapping("/sucartdel.do")
	public ModelAndView sucartDel(int suno ,HttpSession session){

		//카트 객체를 사용하기위하여 카트를 불러옴
		Cart cart = (Cart) session.getAttribute("cart");
		//카트에 상품을 삭제
		cart.deleteCart(suno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cart",cart);
		mav.addObject("center", "SuCartResult.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//회원 가입 폼설정
	@RequestMapping("joinform.do")
	public ModelAndView joinForm(){

		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "JoinForm.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//회원의 정보를 빈클래스를 이용하여 데이터 베이스에 저장
	@RequestMapping("/joinproc")
	public ModelAndView joinProc(MemberBean mbean , HttpSession session){
		ModelAndView mav = new ModelAndView();
		//데이터 베이스에 접근해서 해당 아이디가 존재 하는지를 파악
		int result = shoppingDao.getLogin(mbean);
		//result값이 ==1이라면 해당 아이디가 존재
		if(result==1){

			mav.addObject("result", "1");
			mav.addObject("center", "JoinForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{
			//비밀번호가 두개다 일치 할경우에만 저장
			if(mbean.getMempasswd1().equals(mbean.getMempasswd2())){
				shoppingDao.insertMember(mbean);
				session.setAttribute("mbean", mbean);
				//회원 세션 유지 시간을 설정
				session.setMaxInactiveInterval(60*30);//30분을 의미
				return new ModelAndView(new RedirectView("index.do"));
			}else{
				mav.addObject("result", "2");
				mav.addObject("center", "JoinForm.jsp");
				mav.addObject("left", "SujakLeft.jsp");		
				mav.setViewName("ShoppingMain");
			}			
		}
		return mav;		
	}

	//로그인 메소드
	@RequestMapping("/login.do")
	public ModelAndView loGin(){

		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "LoginForm.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//로그인 처리 메소드
	@RequestMapping("/loginproc.do")
	public ModelAndView loginProc(HttpSession session , MemberBean mbean){

		ModelAndView mav = new ModelAndView();
		//데이터 베이스에 접근하여 해당 아이디와 패스워드가 있는지를 알아주는 메소드호출후 결과를 저장
		int result = shoppingDao.getLoginProc(mbean);
		if(result ==1){//회원이 존재 한다면
			session.setAttribute("mbean", mbean);
			session.setMaxInactiveInterval(60*30);//30분을 의미
			return new ModelAndView(new RedirectView("index.do"));
		}else{//해당 아이디와 패스워드가 없다면
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
			mav.addObject("login", "1");
			return mav;		
		}		
	}

	//로그아웃 처리 메소드
	@RequestMapping("/logout.do")
	public ModelAndView logOut(HttpSession session){

		//회원 정보를 사용하기위해서 세션에서 불러옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//회원 정보를 없에줌
		session.setAttribute("mbean", null);

		return new ModelAndView(new RedirectView("index.do"));
	}

	//상품을  바로 구매하기
	@RequestMapping("/sutoolbuy.do")
	public ModelAndView sutoolBuy(HttpSession session , SuCartBean subean){

		ModelAndView mav = new ModelAndView();
		//회원정보를 세션을 통하여 얻어옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		if(mbean == null){//로그인이 되어있지 않은경우
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{//로그인된경우			
			mav.addObject("subean" ,subean);
			mav.addObject("center", "SutoolBuy.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}

	//카트에 담겨진 모든 상품을 구매하기
	@RequestMapping("/sucartbuy.do")
	public ModelAndView sucartBuy(HttpSession session , SuCartBean subean){

		ModelAndView mav = new ModelAndView();
		//회원정보를 세션을 통하여 얻어옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		if(mbean == null){//로그인이 되어있지 않은경우
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{//로그인된경우		
			//카트에 있는 내용을 계산하기위해서 세션을 통하여 카트 객체를 가져옴
			Cart cart = (Cart) session.getAttribute("cart");			
			mav.addObject("cart" ,cart);
			mav.addObject("center", "SuCartBuy.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}

	//카트에 내용 비우기
	@RequestMapping("/cartalldel.do")
	public ModelAndView cartallDel(HttpSession session){

		//카트 객체를 얻어옴
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();

		return new ModelAndView(new RedirectView("index.do"));

	}

	//스탠리 소개
	@RequestMapping("/stanlyinfo.do")
	public ModelAndView stanlyInfo(String name){
		String [] imgarr ={"stanlycenterinfo","stanlycenterhistory1",
				"stanlycenterglobal","stanlycentercompany"};
		String [] history ={"stanlycenterhistory1","stanlycenterhistory2",
				"stanlycenterhistory3","stanlycenterhistory4"};

		if(name==null){
			name="0";
		}
		if(name=="1"){
			ModelAndView mav = new ModelAndView();
			mav.addObject("name" , name);
			mav.addObject("history", history);
			mav.addObject("center", "StanlyInfoMain.jsp");
			mav.addObject("left", "StanlyInfoLeft.jsp");		
			mav.setViewName("ShoppingMain");
			return mav;
		}else{
			ModelAndView mav = new ModelAndView();
			mav.addObject("imgname", imgarr[Integer.parseInt(name)]);
			mav.addObject("center", "StanlyInfoMain.jsp");
			mav.addObject("left", "StanlyInfoLeft.jsp");		
			mav.setViewName("ShoppingMain");
			return mav;
		}
	}

	//공구 사용법
	@RequestMapping("tooluse.do")
	public ModelAndView toolUse(String name){
		//배열에 이미지의 이름을 미리 담아놓도록
		String [] imgarr={"tool1","tool2","tool3","tool4","tool5",
				"tool6","tool7","tool8-1","tool9","tool10"};


		if(name==null){
			name ="0";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("imgname", imgarr[Integer.parseInt(name)]);
		mav.addObject("center", "ToolUseCenter.jsp");
		mav.addObject("left", "ToolUseLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;	

	}

	//다운로드 클릭시 실행되는 메소드
	@RequestMapping("/download.do")
	public ModelAndView downLoad(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "DownCenter.jsp");
		mav.addObject("left", "DownLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;	
	}

	//실제 서버에서 클라이언트로 다운이 되게 메소드
	@RequestMapping("/downfile.do")
	public ModelAndView downFile(int no){
		
		String [] filename ={"m0.pdf" ,"m1.pdf" ,"m2.pdf" };
		//경로설정
		String path="E:/Spring/workspace/ShoppingMall/WebContent/downfile/";
		
		File downloadfile = new File(path + filename[no]  );
		
		return new ModelAndView("downloadView" , "downloadFile" , downloadfile);
	}
	
	//전체 게시글을 리턴하는 메소드
	@RequestMapping("/board.do")
	public ModelAndView boardList(String pageNum){
		
		ModelAndView mav = new ModelAndView();
		//화면에 보여질 게시글의갯수를 지정
		int pageSize=10;
		
		int count =0;//전체 글의 갯수
		int number =0;//페이지 넘버링수
		
		//처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
		if(pageNum == null){
			pageNum="1";
		}
		int currentPage  = Integer.parseInt(pageNum);
		//게시글의 총 갯수
		count = shoppingDao.getCount();
	
		//현제 페이지에 보여줄 시작 번호를 설정 = 데이터 베이스에서 불러올 시작 번호를 의미
		int startRow = (currentPage -1)*pageSize+1;
		int endRow = currentPage*pageSize;
		//가변길으로 데이터를 저장
		List<BoardBean> vbean=null;
		
		//게시글이 존재한다면
		if(count > 0 ){
			//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
			vbean = shoppingDao.getAllContent(startRow-1 , endRow);		
			//테이블에 표시할 번호를 설정
			number = count -(currentPage -1) * pageSize;
			
		}
		//BoardList.jsp
		mav.addObject("vbean", vbean);
		mav.addObject("number", number);
		mav.addObject("pageSize", pageSize);
		mav.addObject("count", count);
		mav.addObject("currentPage", currentPage);
		mav.addObject("center", "BoardList.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
		
	}
	
	//게시글쓰기
	@RequestMapping("/boardwrite.do")
	public ModelAndView boardWrite(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("center", "BoardWrite.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//게시글 데이터 베이스에 저장
	@RequestMapping("/boardwriteproc.do")
	public ModelAndView boardWriteProc(BoardBean bean){
			
		//데이터베이스로 빈클래스를 떠넘겨줌
		shoppingDao.boardInsert(bean);
		
		return new ModelAndView(new RedirectView("board.do"));
	}
 	
	//게시글 보기
	@RequestMapping("/boardinfo.do")
	public ModelAndView boardInfo(int num){
		
		ModelAndView mav = new ModelAndView();
		//하나의 게시글을 리턴하는 메소드 호출
		BoardBean bean = shoppingDao.getOneContent(num);
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardInfo.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
		
	}
	
	//답글쓰기 폼 
	@RequestMapping("/boardrewrite.do")
	public ModelAndView boardRewrite(BoardBean bean){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardRewrite.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//답글쓰기 처리
	@RequestMapping("/boardrewriteproc.do")
	public ModelAndView boardrewriteProc(BoardBean bean){
		
		//데이터 베이스에 데이터를 저장
		shoppingDao.reWriteboard(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
	//게시글 수정하기
	@RequestMapping("/boardupdate.do")
	public ModelAndView boardUpdate(BoardBean bean){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardUpdate.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//게시글 수정 완료메소드
	@RequestMapping("/boardupdateproc.do")
	public ModelAndView boardupdateProc(BoardBean bean){
		
		shoppingDao.boardUpdate(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
	//게시글 삭제 하기
	@RequestMapping("/boarddelete.do")
	public ModelAndView boardDelete(int num){
	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("num", num);
		mav.addObject("center", "BoardDelete.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	
	}
	//게시글 삭제 하기
	@RequestMapping("/boarddeleteproc.do")
	public ModelAndView boarddeleteProc(BoardBean bean){
	
		shoppingDao.boardDelete(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
}



















