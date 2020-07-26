package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//������ ���̽��� ������ �����Ͽ� ����� ���� �޾��ִ� Ŭ����
public class ShoppingDAO {

	//������ ������Ѽ� �����͸� �����ü� �ֵ������ִ� ��ü���� �������� (preparedstatement��ü�� ��������)
	SimpleJdbcTemplate template;

	//������ ���̽��� �����Ͽ� �����͸� �о���ü� �ִ°�ü
	DataSource dataSource;

	//������� ��ü�� ������Ű���ʰ� dataSource���
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		//��ü ����
		template = new SimpleJdbcTemplate(dataSource);
	}

	//��� ���۾� ������ �������ִ� �޼ҵ�
	public List<SuBean> getAllSutool() {
		//���� �غ�
		String sql ="select * from sutool";
		//��Ŭ����(�÷���� �Ȱ��� Ŭ������  ������ ��ü)�� �÷����� ��Ī�ϴ� 
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.query(sql, rm);
	}

	//���õ� ���۾� ������ �������ִ� �޼ҵ�
	public List<SuBean> getSelectSutool(String num) {
		//���� �غ�
		String sql ="select * from sutool where sucate=?";
		//��Ŭ����(�÷���� �Ȱ��� Ŭ������  ������ ��ü)�� �÷����� ��Ī�ϴ� 
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.query(sql, rm, num);
	}

	//�ϳ��� ���������� �����ϴ� �޼ҵ�
	public SuBean getOneSutool(int suno) {
		String sql="select * from sutool where suno=?";		
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.queryForObject(sql, rm, suno);
	}
	//���̵� �ִ��� �ľ�
	public int getLogin(MemberBean mbean) {
		// ���� �غ�
		String sql="select count(*) from member where memid=?";
		return template.queryForInt(sql, mbean.getMemid());
	}

	public void insertMember(MemberBean mbean) {
		// ���� �ۼ�
		String sql="insert into member values (:memid,:mempasswd1,:mempasswd2,"
				+ ":memname,:memphone,:memdate)";
		//��Ŭ������ �������� 1:1���εǵ��� �ڵ�����
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(mbean);
		//���� �����Ͻÿ�
		template.update(sql, sqlsource);
		
	}

	//�α��� ó���� ���̵�� �н����尡 �ִ����� ���� ���θ� �ľ�
	public int getLoginProc(MemberBean mbean) {
		String sql="select count(*) from member where memid=? and mempasswd1=?";
		return template.queryForInt(sql, mbean.getMemid() , mbean.getMempasswd1());
	}
	
	//�Խ����� ��� �������� ������ ����
	public int getCount() {
		String sql = "select count(*) from board";
		return template.queryForInt(sql);
	}

	//�Խñ��� ���� ��ȣ�� �� ��ȣ�� �������� �����͸� �������ÿ�
	public List<BoardBean> getAllContent(int startRow, int endRow) {
		String sql = "select * from (select A.*, Rownum Rnum from "
				+ "(select * from board order by ref desc, re_level Asc)A)"
				+ "where Rnum>? and Rnum <=?";
		RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
		
		return template.query(sql, rm, startRow-1 , endRow	);
	}

	//�Խñ� �ϳ��� �����ϴ� �޼ҵ�	
	public void boardInsert(BoardBean bean) {
		int ref = 1; //�۱׷�
		int re_step=1;//�θ������ �ڽı�����
		int re_level=1;//���� ������ ������ �����ϴ� ����
		int readcount=0;
		
		//�۱׷��� ���� ������ �˾ƾ� �ҽ��� �ۼ�
		String refmax ="select max(ref) from board";
		int refdata = template.queryForInt(refmax);
		if(refdata >= 1 ){
			ref = refdata+1;
		}
		//��� �����Ͱ� �غ� �Ϸ�Ǿ��ֱ⿡ �����͸� ����
		bean.setRef(ref);
		bean.setRe_step(re_step);
		bean.setRe_level(re_level);
		bean.setReadcount(readcount);
		
		String sql="insert into board values(board_seq.NEXTVAL,:writer,:email,"
				+ ":subject,:passwd,SYSDATE,:readcount,:ref,:re_step,:re_level,:content)";
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
		//��������
		template.update(sql, sqlsource);
		
		
		
	}
	//�ϳ��� �Խñ��� �����ϴ� �޼ҵ�
	public BoardBean getOneContent(int num) {
		
		//��ȸ�� ���� ����
		String countsql ="update board set readcount=readcount+1 where num=?";
		template.update(countsql, num);
		
		//�Խñ۸���
		String sql ="select * from board where num=?";
		RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
		return template.queryForObject(sql, rm, num);
	}

	//��۾���
	public void reWriteboard(BoardBean bean) {
		//�亯�۷� ���Ͽ� ������ �亯�۵��� ������ 1�� �����ϴ� ����
		String levelsql="update board set re_level=re_level+1 where ref=? and re_level >?";
		template.update(levelsql, bean.getRef(), bean.getRe_level());
		
		//���� �亯���� �����ϴ� �޼ҵ�
		bean.setRe_step(bean.getRe_step()+1);
		bean.setRe_level(bean.getRe_level()+1);
		
		String sql="insert into board values(board_seq.NEXTVAL,:writer,:email,"
				+ ":subject,:passwd,SYSDATE,:readcount,:ref,:re_step,:re_level,:content)";
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
		//��������
		template.update(sql, sqlsource);
	}

	//�Խñ� ���� 
	public void boardUpdate(BoardBean bean) {
		String sql="update board set content=? where num=? and passwd=?";
		template.update(sql, bean.getContent(), bean.getNum(), bean.getPasswd());
	}

	//�Խñ� ����
	public void boardDelete(BoardBean bean) {
		String sql="delete from board where num=? and passwd=?";
		template.update(sql, bean.getNum(), bean.getPasswd());
		
	}

	


}








