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

	//�������
	public void setShoppingDao(ShoppingDAO shoppingDao) {
		this.shoppingDao = shoppingDao;
	}


	@RequestMapping("/index.do")//index.do��� ��û�� ������ ����Ǵ� �޼ҵ�
	public ModelAndView index(HttpSession session){//ȸ�� ���� ������ ����ϱ����Ͽ� ������ ����

		//�����Ϳ� jsp�� �������ִ� ��ü ����
		ModelAndView mav = new ModelAndView();
		//ȸ�� ������ ����ϱ����Ͽ� ���ǰ�ü�� �ҷ���
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//������ �̿��Ͽ� �α���ó��
		if(mbean==null){//top.jsp���� �α��� ������ ó���ϱ����� �ҽ�
			mav.addObject("mbean" , null);
			mav.setViewName("ShoppingMain");			
		}else{
			mav.addObject("mbean" , mbean);
			mav.setViewName("ShoppingMain");		
		}

		return mav;		
	}



	//���۾� ������ �����ٸ� �ڵ����� ȣ��Ǵ� �޼ҵ�
	@RequestMapping("/sujak.do")
	public ModelAndView suJak(String num){

		//������ ��ü ����
		ModelAndView mav = new ModelAndView();
		//��� ��û�� ���Դ����� ���� ������ ���ͼ� �ش� model�� �����Ͽ� �����͸� ����������
		if(num==null){ // ����ǰ�� �����ų� top�� �ִ� �޴��� ���۾� ������ư�� ������			
			List<SuBean> list = shoppingDao.getAllSutool();
			mav.addObject("list", list);
		}else { // �ش�޴����� ���ý� ���Ǵ� �ҽ�		
			List<SuBean> list = shoppingDao.getSelectSutool(num);
			mav.addObject("list", list);
		}	

		mav.addObject("center" ,"SujakCenter.jsp");
		mav.addObject("left", "SujakLeft.jsp");

		mav.setViewName("ShoppingMain");
		return mav;
	}

	//���� �ϳ��� ������ �����ִ� �޼ҵ�
	@RequestMapping("/suinfo.do")
	public ModelAndView suInfo(int suno){

		SuBean sbean = shoppingDao.getOneSutool(suno);

		//View ������ �����͸� ���Ѱ���
		ModelAndView mav =new ModelAndView();
		mav.addObject("sbean", sbean);
		mav.addObject("center", "SuJakInfo.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//īƮ�� ��� ��ư�� ������ ȣ��Ǵ� �޼ҵ�
	@RequestMapping("/sutoolcart.do")
	public ModelAndView sutoolCart(SuCartBean cartbean , HttpSession session ){
		//īƮ�� ����Ǵ� ��Ŭ������ SuCartBean

		//īƮ�� ����ϱ����ؼ� CartŬ������ ���
		Cart cart = (Cart) session.getAttribute("cart");
		//������ īƮŬ������ ���ٸ� 
		if(cart == null){
			//īƮ ��ü�� �������Ŀ� ���ǿ� ����
			cart = new Cart();
			session.setAttribute("cart", cart);//���� ��Ʈ���� ���������� ��� ��īƮ�� ����
		}
		//īƮ�� �Ѿ�� ��ǰ�� �߰�
		cart.push(cartbean);

		ModelAndView mav = new ModelAndView();

		mav.addObject("msg", cartbean.getSuname()+" �� ��ǰ�� " +cartbean.getSuqty()
				+ "�� īƮ�� �߰��߽��ϴ�." );
		mav.addObject("cart",cart);
		mav.addObject("center", "SuCartResult.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}


	//īƮ�� �ϳ��� ����� ���� �ϴ� �޼ҵ�
	@RequestMapping("/sucartdel.do")
	public ModelAndView sucartDel(int suno ,HttpSession session){

		//īƮ ��ü�� ����ϱ����Ͽ� īƮ�� �ҷ���
		Cart cart = (Cart) session.getAttribute("cart");
		//īƮ�� ��ǰ�� ����
		cart.deleteCart(suno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cart",cart);
		mav.addObject("center", "SuCartResult.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//ȸ�� ���� ������
	@RequestMapping("joinform.do")
	public ModelAndView joinForm(){

		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "JoinForm.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//ȸ���� ������ ��Ŭ������ �̿��Ͽ� ������ ���̽��� ����
	@RequestMapping("/joinproc")
	public ModelAndView joinProc(MemberBean mbean , HttpSession session){
		ModelAndView mav = new ModelAndView();
		//������ ���̽��� �����ؼ� �ش� ���̵� ���� �ϴ����� �ľ�
		int result = shoppingDao.getLogin(mbean);
		//result���� ==1�̶�� �ش� ���̵� ����
		if(result==1){

			mav.addObject("result", "1");
			mav.addObject("center", "JoinForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{
			//��й�ȣ�� �ΰ��� ��ġ �Ұ�쿡�� ����
			if(mbean.getMempasswd1().equals(mbean.getMempasswd2())){
				shoppingDao.insertMember(mbean);
				session.setAttribute("mbean", mbean);
				//ȸ�� ���� ���� �ð��� ����
				session.setMaxInactiveInterval(60*30);//30���� �ǹ�
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

	//�α��� �޼ҵ�
	@RequestMapping("/login.do")
	public ModelAndView loGin(){

		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "LoginForm.jsp");
		mav.addObject("left", "SujakLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;
	}

	//�α��� ó�� �޼ҵ�
	@RequestMapping("/loginproc.do")
	public ModelAndView loginProc(HttpSession session , MemberBean mbean){

		ModelAndView mav = new ModelAndView();
		//������ ���̽��� �����Ͽ� �ش� ���̵�� �н����尡 �ִ����� �˾��ִ� �޼ҵ�ȣ���� ����� ����
		int result = shoppingDao.getLoginProc(mbean);
		if(result ==1){//ȸ���� ���� �Ѵٸ�
			session.setAttribute("mbean", mbean);
			session.setMaxInactiveInterval(60*30);//30���� �ǹ�
			return new ModelAndView(new RedirectView("index.do"));
		}else{//�ش� ���̵�� �н����尡 ���ٸ�
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
			mav.addObject("login", "1");
			return mav;		
		}		
	}

	//�α׾ƿ� ó�� �޼ҵ�
	@RequestMapping("/logout.do")
	public ModelAndView logOut(HttpSession session){

		//ȸ�� ������ ����ϱ����ؼ� ���ǿ��� �ҷ���
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//ȸ�� ������ ������
		session.setAttribute("mbean", null);

		return new ModelAndView(new RedirectView("index.do"));
	}

	//��ǰ��  �ٷ� �����ϱ�
	@RequestMapping("/sutoolbuy.do")
	public ModelAndView sutoolBuy(HttpSession session , SuCartBean subean){

		ModelAndView mav = new ModelAndView();
		//ȸ�������� ������ ���Ͽ� ����
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		if(mbean == null){//�α����� �Ǿ����� �������
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{//�α��εȰ��			
			mav.addObject("subean" ,subean);
			mav.addObject("center", "SutoolBuy.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}

	//īƮ�� ����� ��� ��ǰ�� �����ϱ�
	@RequestMapping("/sucartbuy.do")
	public ModelAndView sucartBuy(HttpSession session , SuCartBean subean){

		ModelAndView mav = new ModelAndView();
		//ȸ�������� ������ ���Ͽ� ����
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		if(mbean == null){//�α����� �Ǿ����� �������
			mav.addObject("center", "LoginForm.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");

		}else{//�α��εȰ��		
			//īƮ�� �ִ� ������ ����ϱ����ؼ� ������ ���Ͽ� īƮ ��ü�� ������
			Cart cart = (Cart) session.getAttribute("cart");			
			mav.addObject("cart" ,cart);
			mav.addObject("center", "SuCartBuy.jsp");
			mav.addObject("left", "SujakLeft.jsp");		
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}

	//īƮ�� ���� ����
	@RequestMapping("/cartalldel.do")
	public ModelAndView cartallDel(HttpSession session){

		//īƮ ��ü�� ����
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();

		return new ModelAndView(new RedirectView("index.do"));

	}

	//���ĸ� �Ұ�
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

	//���� ����
	@RequestMapping("tooluse.do")
	public ModelAndView toolUse(String name){
		//�迭�� �̹����� �̸��� �̸� ��Ƴ�����
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

	//�ٿ�ε� Ŭ���� ����Ǵ� �޼ҵ�
	@RequestMapping("/download.do")
	public ModelAndView downLoad(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center", "DownCenter.jsp");
		mav.addObject("left", "DownLeft.jsp");		
		mav.setViewName("ShoppingMain");
		return mav;	
	}

	//���� �������� Ŭ���̾�Ʈ�� �ٿ��� �ǰ� �޼ҵ�
	@RequestMapping("/downfile.do")
	public ModelAndView downFile(int no){
		
		String [] filename ={"m0.pdf" ,"m1.pdf" ,"m2.pdf" };
		//��μ���
		String path="E:/Spring/workspace/ShoppingMall/WebContent/downfile/";
		
		File downloadfile = new File(path + filename[no]  );
		
		return new ModelAndView("downloadView" , "downloadFile" , downloadfile);
	}
	
	//��ü �Խñ��� �����ϴ� �޼ҵ�
	@RequestMapping("/board.do")
	public ModelAndView boardList(String pageNum){
		
		ModelAndView mav = new ModelAndView();
		//ȭ�鿡 ������ �Խñ��ǰ����� ����
		int pageSize=10;
		
		int count =0;//��ü ���� ����
		int number =0;//������ �ѹ�����
		
		//ó�� �Խñ� ���⸦ ������ pageNum���⿡ nulló�����־���մϴ�.
		if(pageNum == null){
			pageNum="1";
		}
		int currentPage  = Integer.parseInt(pageNum);
		//�Խñ��� �� ����
		count = shoppingDao.getCount();
	
		//���� �������� ������ ���� ��ȣ�� ���� = ������ ���̽����� �ҷ��� ���� ��ȣ�� �ǹ�
		int startRow = (currentPage -1)*pageSize+1;
		int endRow = currentPage*pageSize;
		//���������� �����͸� ����
		List<BoardBean> vbean=null;
		
		//�Խñ��� �����Ѵٸ�
		if(count > 0 ){
			//10���� �������� �����͸� ������ ���̽����� �о�帲
			vbean = shoppingDao.getAllContent(startRow-1 , endRow);		
			//���̺� ǥ���� ��ȣ�� ����
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
	
	//�Խñ۾���
	@RequestMapping("/boardwrite.do")
	public ModelAndView boardWrite(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("center", "BoardWrite.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//�Խñ� ������ ���̽��� ����
	@RequestMapping("/boardwriteproc.do")
	public ModelAndView boardWriteProc(BoardBean bean){
			
		//�����ͺ��̽��� ��Ŭ������ ���Ѱ���
		shoppingDao.boardInsert(bean);
		
		return new ModelAndView(new RedirectView("board.do"));
	}
 	
	//�Խñ� ����
	@RequestMapping("/boardinfo.do")
	public ModelAndView boardInfo(int num){
		
		ModelAndView mav = new ModelAndView();
		//�ϳ��� �Խñ��� �����ϴ� �޼ҵ� ȣ��
		BoardBean bean = shoppingDao.getOneContent(num);
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardInfo.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
		
	}
	
	//��۾��� �� 
	@RequestMapping("/boardrewrite.do")
	public ModelAndView boardRewrite(BoardBean bean){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardRewrite.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//��۾��� ó��
	@RequestMapping("/boardrewriteproc.do")
	public ModelAndView boardrewriteProc(BoardBean bean){
		
		//������ ���̽��� �����͸� ����
		shoppingDao.reWriteboard(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
	//�Խñ� �����ϱ�
	@RequestMapping("/boardupdate.do")
	public ModelAndView boardUpdate(BoardBean bean){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bean", bean);
		mav.addObject("center", "BoardUpdate.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;	
	}
	
	//�Խñ� ���� �Ϸ�޼ҵ�
	@RequestMapping("/boardupdateproc.do")
	public ModelAndView boardupdateProc(BoardBean bean){
		
		shoppingDao.boardUpdate(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
	//�Խñ� ���� �ϱ�
	@RequestMapping("/boarddelete.do")
	public ModelAndView boardDelete(int num){
	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("num", num);
		mav.addObject("center", "BoardDelete.jsp");
		mav.addObject("left", "BoardLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	
	}
	//�Խñ� ���� �ϱ�
	@RequestMapping("/boarddeleteproc.do")
	public ModelAndView boarddeleteProc(BoardBean bean){
	
		shoppingDao.boardDelete(bean);
		return new ModelAndView(new RedirectView("board.do"));
	}
	
}



















