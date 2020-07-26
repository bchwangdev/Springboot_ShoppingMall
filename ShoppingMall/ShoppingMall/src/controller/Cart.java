package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.SuCartBean;

//상품에 내용을 list에 저장하고 또는 기존에 상품이 있다면 수량만 증가하는 클래스,삭제기능과 모든 카트내용삭제
public class Cart {
	
	//카트에 여러개의 상품을 저장하기위한 객체 선언
	private List<SuCartBean> itemlist = new ArrayList<SuCartBean>();

	public List<SuCartBean> getItemlist() {
		return itemlist;
	}
	
	//카트에 상품을 추가하는 메소드를 작성 = 기존에 상품이 있다면 수량만 증가하고 없다면 모두 저장하시오
	public void push(SuCartBean cartbean){
		//기존에 상품이 있는지를 알아내는 변수
		boolean alreadysutool=false;
		
		//itemlist안에 데이터를 반복문을 돌면서 기존에 있는 데이터가 있는지 비교를
		for (SuCartBean suCartBean : itemlist) {
			if(cartbean.getSuno() == suCartBean.getSuno()){
				//동일한 상품이 존재 하기에 수량만 증가
				suCartBean.setSuqty(suCartBean.getSuqty()+cartbean.getSuqty());
				alreadysutool =true;
				break;
			}			
		}
		
		//기존에 상품이 없는 경우에는 상품을 추가 시켜주어야 합니다.
		if(alreadysutool== false){
			itemlist.add(cartbean);
		}
		
	}
	
	//카트의 하나의 상품을 삭제 해주는 메소드 
	public void deleteCart(int suno){
		//반복문을 돌면서 해당 공구의 번호를 기준으로 삭제
		for (SuCartBean suCartBean : itemlist) {
			//삭제하고자 하는 번호가 있다면 itemlist 에서 해당 상품을 삭제
			if(suCartBean.getSuno() == suno){
				itemlist.remove(suCartBean);
				break;
			}
		}		
	}
	
	
	//카트 비우기
	public void clearCart(){
		itemlist.removeAll(itemlist);
	}

}













