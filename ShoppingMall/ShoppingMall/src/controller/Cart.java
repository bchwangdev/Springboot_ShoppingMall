package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.SuCartBean;

//��ǰ�� ������ list�� �����ϰ� �Ǵ� ������ ��ǰ�� �ִٸ� ������ �����ϴ� Ŭ����,������ɰ� ��� īƮ�������
public class Cart {
	
	//īƮ�� �������� ��ǰ�� �����ϱ����� ��ü ����
	private List<SuCartBean> itemlist = new ArrayList<SuCartBean>();

	public List<SuCartBean> getItemlist() {
		return itemlist;
	}
	
	//īƮ�� ��ǰ�� �߰��ϴ� �޼ҵ带 �ۼ� = ������ ��ǰ�� �ִٸ� ������ �����ϰ� ���ٸ� ��� �����Ͻÿ�
	public void push(SuCartBean cartbean){
		//������ ��ǰ�� �ִ����� �˾Ƴ��� ����
		boolean alreadysutool=false;
		
		//itemlist�ȿ� �����͸� �ݺ����� ���鼭 ������ �ִ� �����Ͱ� �ִ��� �񱳸�
		for (SuCartBean suCartBean : itemlist) {
			if(cartbean.getSuno() == suCartBean.getSuno()){
				//������ ��ǰ�� ���� �ϱ⿡ ������ ����
				suCartBean.setSuqty(suCartBean.getSuqty()+cartbean.getSuqty());
				alreadysutool =true;
				break;
			}			
		}
		
		//������ ��ǰ�� ���� ��쿡�� ��ǰ�� �߰� �����־�� �մϴ�.
		if(alreadysutool== false){
			itemlist.add(cartbean);
		}
		
	}
	
	//īƮ�� �ϳ��� ��ǰ�� ���� ���ִ� �޼ҵ� 
	public void deleteCart(int suno){
		//�ݺ����� ���鼭 �ش� ������ ��ȣ�� �������� ����
		for (SuCartBean suCartBean : itemlist) {
			//�����ϰ��� �ϴ� ��ȣ�� �ִٸ� itemlist ���� �ش� ��ǰ�� ����
			if(suCartBean.getSuno() == suno){
				itemlist.remove(suCartBean);
				break;
			}
		}		
	}
	
	
	//īƮ ����
	public void clearCart(){
		itemlist.removeAll(itemlist);
	}

}













